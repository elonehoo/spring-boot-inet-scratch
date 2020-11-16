package com.inet.code.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.inet.code.entity.User;
import com.inet.code.mapper.UserMapper;
import com.inet.code.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inet.code.utlis.FromMailUtil;
import com.inet.code.utlis.JwtUtils;
import com.inet.code.utlis.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-11-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录操作
     * @author HCY
     * @since 2020-11-13
     * @param account 账号
     * @param password 密码
     * @param path URL路径
     * @return Result风格
     */
    @Override
    public Result getLogin(String account, String password, String path) {
        //通过账号或者密码进行登录操作
        User user = userMapper.getLogin(
                account
                ,DigestUtil.md5Hex(password));
        //账号或者密码错误
        if (user == null){
            return new Result(
                    Result.STATUS_NOT_FOUND_404
                    ,Result.INFO_NOT_FOUND_404
                    ,Result.DETAILS_NOT_FOUND_404
                    ,"您的账号或者密码错误"
                    ,path);
        }
        //产生 token 的条件
        Map<String, String> map = new HashMap<>(2);
        map.put("userName",user.getUserName());
        map.put("userId",user.getUserUuid());
        //产生 token
        String token = JwtUtils.getToken(map);
        //存入 Redis
        redisTemplate.opsForValue().set(token,user,7, TimeUnit.DAYS);
        //创建返回值
        Map<String, String> results = new HashMap<>(2);
        results.put("info","登录成功");
        results.put("token",token);
        return new Result(
                Result.STATUS_OK_200
                ,Result.INFO_OK_200
                ,"成功"
                ,results
                ,path);
    }

    /**
     * 退出
     * @author HCY
     * @since 2020-11-14
     * @param token 令牌
     * @param path URL路径
     * @return Result风格
     */
    @Override
    public Result getExit(String token, String path) {
        if (redisTemplate.delete(token)) {
            return new Result(
                    Result.STATUS_OK_200
                    ,Result.INFO_OK_200
                    ,Result.DETAILS_OK_200
                    ,"退出成功"
                    ,path);
        }
        return  new Result(
                Result.STATUS_ERROR_500
                ,Result.INFO_ERROR_500
                ,Result.DETAILS_ERROR_500
                ,"退出失败"
                ,path);
    }

    /**
     * 修改用户信息
     * @author HCY
     * @since 2020-11-14
     * @param token 令牌
     * @param buddha 头像
     * @param name 用户名字
     * @param sex 性别
     * @param birthday 生日
     * @param city 地址
     * @param signature 个性签名
     * @param path URL路径
     * @return Result风格
     */
    @Override
    public Result getUpload(String token, String buddha, String name, Boolean sex
            , String birthday, String city, String signature, String path) {
        //通过Token获取用户的具体信息
        User user = (User) redisTemplate.opsForValue().get(token);
        //如果头像为空，则不修改
        if (!("".equals(buddha))){
            user.setUserBuddha(buddha);
        }
        //如果用户名称为空，则不修改
        if (!"".equals(name)){
            user.setUserName(name);
        }
        //如果用户性别进行了改变
        if (!user.getUserSex().equals(sex)){
            user.setUserSex(sex);
        }
        //如果用户的生日不为空
        if (!"".equals(birthday)){
            user.setUserBirthday(DateUtil.parse(birthday,"yyyy-MM-dd HH:mm:ss"));
        }
        //如果地址不为空
        if (!"".equals(city)){
            user.setUserCity(city);
        }
        //如果个性签名不为空
        if (!"".equals(signature)){
            user.setUserSignature(signature);
        }
        //进行存储操作
        boolean consequence = this.updateById(user);
        //判断是否修改成功
        if (consequence){
            return new Result(
                    Result.STATUS_OK_200
                    ,Result.INFO_OK_200
                    ,Result.DETAILS_OK_200
                    ,"修改成功"
                    ,path);
        }else {
            return new Result(
                    Result.STATUS_ERROR_500
                    ,Result.INFO_ERROR_500
                    ,Result.DETAILS_ERROR_500
                    ,"修改失败"
                    ,path);
        }
    }

    /**
     * 发送邮箱验证码
     * @author HCY
     * @since 2020-11-15
     * @param email 邮箱
     * @param path URL路径
     * @return Result风格
     */
    @Override
    public Result getVerification(String email, String path) {
        //判断邮箱是否合法
        if (!Validator.isEmail(email)) {
            return new Result(
                    Result.STATUS_ILLEGAL_401
                    ,Result.INFO_ILLEGAL_401
                    ,Result.DETAILS_ILLEGAL_401
                    ,"邮箱不合法，无法进行发送验证码"
                    ,path);
        }
        //判断邮箱是否重复
        Result emailRepeat = this.getEmailRepeat(email, path);
        //判断邮箱是否重复
        if (!emailRepeat.getStatus().equals(Result.STATUS_OK_200)){
            return emailRepeat;
        }
        //产生验证码
        String code = RandomUtil.randomString(5);
        //设置邮件的内容
        MailAccount account = this.getMail();
        //发送邮件
        MailUtil.send(
                account
                , email
                , "博语兼程社区验证码"
                , "注册验证码为:" + code + ",有效时长为5分钟"
                , false);
        //将验证码存入Redis
        redisTemplate.opsForValue().set(
                email
                ,code
                ,60 * 5
                ,TimeUnit.SECONDS);
        //递交返回值
        return new Result(
                Result.STATUS_OK_200
                ,Result.INFO_OK_200
                ,Result.DETAILS_OK_200
                ,"验证码发送成功"
                ,path);
    }

    /**
     * 设置邮箱的配置文件
     * @author HCY
     * @since 2020-11-16
     * @return MailAccount
     */
    private MailAccount getMail() {
        MailAccount account = new MailAccount();
        account.setHost(FromMailUtil.HOST);
        account.setPort(FromMailUtil.PORT);
        account.setAuth(true);
        account.setFrom(FromMailUtil.FROM);
        account.setUser(FromMailUtil.USER);
        account.setPass(FromMailUtil.PASS);
        return account;
    }

    /**
     * 判断邮箱是否重复
     * @author HCY
     * @since 2020-11-15
     * @param email 邮箱
     * @param path URL路径
     * @return Result风格
     */
    @Override
    public Result getEmailRepeat(String email, String path) {
        if (userMapper.getEMailRepeat(email) == null){
            return new Result(
                    Result.STATUS_ERROR_500
                    ,Result.INFO_ERROR_500
                    ,Result.DETAILS_ERROR_500
                    ,"邮箱产生了重复"
                    ,path);
        }
        return new Result(
                Result.STATUS_OK_200
                ,Result.INFO_OK_200
                , Result.DETAILS_OK_200
                ,"邮箱未产生重复"
                ,path);
    }

    /**
     * 通过邮箱注册
     * @author HCY
     * @since 2020-11-16
     * @param email 邮箱
     * @param code 验证码
     * @param password 密码
     * @param path URL路径
     * @return Result风格
     */
    @Override
    public Result getRegister(String email, String code, String password, String path) {
        //判断邮箱是否合法
        if (!Validator.isEmail(email)){
            return new Result(
                    Result.STATUS_ILLEGAL_401
                    ,Result.INFO_ILLEGAL_401
                    ,Result.DETAILS_ILLEGAL_401
                    ,"邮箱不合法"
                    ,path);
        }
        //判断验证码是否正确
        String verification = (String) redisTemplate.opsForValue().get(email);
        if (!verification.equals(code)){
            return new Result(
                    Result.STATUS_BAN_403
                    ,Result.INFO_BAN_403
                    ,Result.DETAILS_BAN_403
                    ,"验证码错误"
                    ,path);
        }
        //判断密码是否合法
        if (! Validator.isGeneral(password)) {
            return new Result(
                    Result.STATUS_ILLEGAL_401
                    ,Result.INFO_ILLEGAL_401
                    ,Result.DETAILS_ILLEGAL_401
                    ,"密码格式不正确，需要包含大写字母，小写字母，数字"
                    ,path);
        }
        //进行注册操作，注册的用户权限为 ： member
        User user = new User();

        return null;
    }

}
