package com.inet.code.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.inet.code.entity.*;
import com.inet.code.mapper.UserMapper;
import com.inet.code.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inet.code.utlis.FromMailUtil;
import com.inet.code.utlis.JwtUtils;
import com.inet.code.utlis.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    private PortraitService portraitService;

    @Resource
    private CipherService cipherService;

    @Resource
    private RoleService roleService;

    @Resource
    private PowerService powerService;

    @Resource
    private AttentionService attentionService;

    @Resource
    private ParaiseService paraiseService;

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
            return new Result().result404("您的账号或者密码错误",path);
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
        return new Result().result200(results,path);
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
            return new Result().result200("退出成功",path);
        }
        return  new Result().result500("退出失败",path);
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
            return new Result().result200("修改成功",path);
        }else {
            return new Result().result500("修改失败",path);
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
            return new Result().result401("邮箱不合法，无法进行发送验证码",path);
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
        return new Result().result200("验证码发送成功",path);
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
        if (userMapper.getEMailRepeat(email) != null){
            return new Result().result500("邮箱产生了重复",path);
        }
        return new Result().result200("邮箱未产生重复",path);
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
            return new Result().result401("邮箱不合法",path);
        }
        //判断邮箱是否重复
        Result emailRepeat = this.getEmailRepeat(email, path);
        //判断邮箱是否重复
        if (!emailRepeat.getStatus().equals(Result.STATUS_OK_200)){
            return emailRepeat;
        }
        //判断验证码是否正确
        String verification = (String) redisTemplate.opsForValue().get(email);
        if (!verification.equals(code)){
            return new Result().result403("验证码错误",path);
        }
        //判断密码是否合法
        if (! Validator.isGeneral(password)) {
            return new Result().result401("密码格式不正确，需要包含大写字母，小写字母，数字",path);
        }
        //进行注册操作，注册的用户权限为 ： member
        User user = new User();
        user.setUserBuddha(portraitService.getRandomImagesUrl().getPortraitSrc());
        user.setUserName(email);
        user.setUserEmail(email);
        user.setUserCreation(new Date());
        user.setUserModification(new Date());
        this.save(user);
        //设置密码
        Cipher cipher = new Cipher();
        cipher.setCipherEmail(email);
        cipher.setCipherPassword(DigestUtil.md5Hex(password));
        cipher.setCipherCreation(new Date());
        cipher.setCipherModification(new Date());
        cipherService.save(cipher);
        //设置权限 - member
        Power power = new Power();
        power.setPowerEmail(email);
        power.setPowerRole(roleService.getRoleName("member").getRoleUuid());
        power.setPowerCreation(new Date());
        power.setPowerModification(new Date());
        powerService.save(power);
        return new Result().result200("注册成功",path);
    }

    /**
     * 进行密码的修改
     * @author HCY
     * @since 2020-11-16
     * @param token 令牌
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param path URL路径
     * @return Result风格
     */
    @Override
    public Result getChangePassword(String token, String oldPassword, String newPassword, String path) {
        User user = (User) redisTemplate.opsForValue().get(token);
        //判断token是否有用户
        if (user == null){
            return new Result().result401("尚未找到用户",path);
        }
        //取出密码
        Cipher userCipher = cipherService.getByEmail(user.getUserEmail());
        //判断旧密码是否正确
        if (! userCipher.getCipherPassword().equals(DigestUtil.md5Hex(oldPassword))){
            return new Result().result404("旧密码不正确",path);
        }
        //判断新密码是否符合密码规范
        if (! Validator.isGeneral(newPassword)) {
            return new Result().result401("密码格式不正确，需要包含大写字母，小写字母，数字",path);
        }
        //进行密码的修改
        userCipher.setCipherPassword(DigestUtil.md5Hex(newPassword));
        if ( ! cipherService.updateById(userCipher)){
            return new Result().result500("修改失败",path);
        }
        return new Result().result200("修改成功",path);
    }

    /**
     * 进行关注或者取关处理
     * @author HCY
     * @since 2020-11-16
     * @param token 令牌
     * @param focusEmail 关注者邮箱
     * @param path URL路径
     * @return Result风格
     */
    @Override
    public Result getFocus(String token, String focusEmail, String path) {
        //通过 token 获取用户 user
        User user = (User) redisTemplate.opsForValue().get(token);
        //判断用户或者关注的用户是否存在
        Result fOEO = focusOnEarlyOperations(focusEmail, path, user);
        if (fOEO != null){
            return fOEO;
        }
        //判断是关注操作还是取关的操作
        Result result = null;
        if (attentionService.getWhetherAttention(user.getUserEmail() , focusEmail) == null){
            //关注操作
            result = attentionOperating(user,focusEmail,path);
        }else {
            //取关操作
            result = takeOffOperating(user,focusEmail,path);
        }

        return result;
    }

    /**
     * 进行点赞操作,如果已经点赞过了,进行取消
     * @author HCY
     * @since 2020-11-19
     * @param token 令牌
     * @param thumbUpEmail 进行点赞的邮箱
     * @param path URL路径
     * @return Result风格
     */
    @Override
    public Result getLike(String token, String thumbUpEmail, String path) {
        //从redis中取出用户信息
        User user = (User) redisTemplate.opsForValue().get(token);
        //判断用户信息和点赞的用户是否正确
        Result userLike = judgeUserLike(user,thumbUpEmail,path);
        if (user != null){
            return userLike;
        }
        //判断是点赞还是取消点赞的操作
        Result result = null;
        if (paraiseService.getJudgeUserLike(user.getUserEmail(),thumbUpEmail) == null){
            //点赞操作
            result = clickLike(user.getUserEmail() ,thumbUpEmail,path);
        }else {
            //取消点赞操作
            result = trampleLike(user.getUserEmail(),thumbUpEmail,path);
        }
        return result;
    }

    /**
     * 取消点赞操作
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param thumbUpEmail 需要点赞的邮箱
     * @param path URL路径
     * @return Result
     */
    private Result trampleLike(String userEmail, String thumbUpEmail, String path) {
        if (paraiseService.getTrampleLike(userEmail , thumbUpEmail)) {
            return new Result().result200("取消点赞成功",path);
        }
        return new Result().result500("取消点赞失败",path);
    }

    /**
     * 点赞操作
     * @author HCY
     * @since 2020-11-19
     * @param userEmail 用户邮箱
     * @param thumbUpEmail 需要点赞的邮箱
     * @param path URL路径
     * @return Result
     */
    private Result clickLike(String userEmail, String thumbUpEmail, String path) {
        //创建实体类
        Paraise paraise = new Paraise();
        //设置用户邮箱邮箱
        paraise.setPraiseEmail(userEmail);
        //设置需要点赞的邮箱
        paraise.setPraiseEconomy(thumbUpEmail);
        //设置创建和修改的时间
        paraise.setPraiseCreation(new Date());
        paraise.setPraiseModification(new Date());
        //进行存储
        if (paraiseService.save(paraise)) {
            return new Result().result200("点赞成功",path);
        }
        return new Result().result500("点赞失败",path);
    }

    /**
     * 判断用户是否存在
     * 判断需要点赞用户是否存在
     * 判断是否正在点赞自己
     * 如果都正确则输出null
     * @author HCY
     * @since 2020-11-19
     * @param thumbUpEmail 点赞者的邮箱
     * @param path URL路径
     * @param user User对象
     * @return Result风格
     */
    private Result judgeUserLike(User user, String thumbUpEmail, String path) {
        //判断user是否存在
        if (user == null){
            return new Result().result404("用户不正确，请重新登陆", path);
        }
        //判断需要关注的用户是否存在
        if (userMapper.getByEmail(thumbUpEmail) == null){
            return new Result().result404("点赞的用户不存在，请刷新页面在进行关注", path);
        }
        //判断是否在关注自己
        if (user.getUserEmail().equals(thumbUpEmail)){
            return  new Result().result401("您一直在点赞自己了哦！", path);
        }
        return null;
    }

    /**
     * 取消关注操作
     * @author HCY
     * @since 2020-11-19
     * @param user 用户
     * @param focusEmail 需要关注的邮箱
     * @param path URL路径
     * @return Result
     */
    private Result takeOffOperating(User user, String focusEmail, String path) {
        if (attentionService.takeOffOperating(user.getUserEmail(),focusEmail)) {
            return new Result().result200("删除成功",path);
        }
        return new Result().result500("删除失败",path);
    }

    /**
     * 关注操作
     * @author HCY
     * @since 2020-11-19
     * @param user 用户
     * @param focusEmail 需要关注的邮箱用户
     * @param path URL路径
     * @return Result
     */
    private Result attentionOperating(User user, String focusEmail, String path) {
        //进行关注操作
        Attention attention = new Attention();
        //设置用户的邮箱
        attention.setAttentionEmail(user.getUserEmail());
        //设置需要关注的邮箱
        attention.setAttentionConcern(focusEmail);
        //设置创建时间和修改时间
        attention.setAttentionCreation(new Date());
        attention.setAttentionModification(new Date());
        //进行保存操作
        if (attentionService.save(attention)) {
            return new Result().result200("关注成功", path);
        }
        return new Result().result500("关注失败!",path);
    }

    /**
     * 判断用户是否存在
     * 判断需要关注的用户是否存在
     * 判断是否正在关注自己
     * 如果都正确则输出null
     * @author HCY
     * @since 2020-11-19
     * @param focusEmail 关注者的邮箱
     * @param path URL路径
     * @param user User对象
     * @return Result风格
     */
    private Result focusOnEarlyOperations(String focusEmail, String path, User user) {
        //判断user是否存在
        if (user == null){
            return new Result().result404("用户不正确，请重新登陆", path);
        }
        //判断需要关注的用户是否存在
        if (userMapper.getByEmail(focusEmail) == null){
            return new Result().result404("关注的用户不存在，请刷新页面在进行关注", path);
        }
        //判断是否在关注自己
        if (user.getUserEmail().equals(focusEmail)){
            return  new Result().result401("您一直在关注自己了哦！", path);
        }
        return null;
    }

}
