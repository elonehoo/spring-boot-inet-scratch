package com.inet.code.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.inet.code.entity.User;
import com.inet.code.mapper.UserMapper;
import com.inet.code.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inet.code.utlis.JwtUtils;
import com.inet.code.utlis.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
                    404
                    ,"not found"
                    ,"未找到"
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
        redisTemplate.opsForValue().set(token,user);
        //创建返回值
        Map<String, String> results = new HashMap<>(2);
        results.put("info","登录成功");
        results.put("token",token);
        return new Result(
                200
                ,"OK"
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
            return new Result(200,"OK","成功","退出成功",path);
        }
        return  new Result(500,"Error","错误","退出失败",path);
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
    public Result getUpload(String token, String buddha, String name, Boolean sex, String birthday, String city, String signature, String path) {
        //通过Token获取用户的具体信息
        User user = (User) redisTemplate.opsForValue().get(token);
        //如果头像为空，则不修改
        if (!("".equals(buddha))){
            user.setUserBuddha(buddha);
        }
        //如果用户名称为空，则不修改
        if (!("".equals(name))){
            user.setRoleName(name);
        }
        //如果用户性别不为空
        if(user.getUserSex() != sex){

        }
        return null;
    }
}
