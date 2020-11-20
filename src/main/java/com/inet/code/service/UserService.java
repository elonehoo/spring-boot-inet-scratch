package com.inet.code.service;

import com.inet.code.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.inet.code.utlis.Result;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HCY
 * @since 2020-11-14
 */
public interface UserService extends IService<User> {
    /**
     * 登录操作
     * @author HCY
     * @since 2020-11-13
     * @param account 账号
     * @param password 密码
     * @param path URL路径
     * @return Result风格
     */
    Result getLogin(String account, String password, String path);

    /**
     * 退出
     * @author HCY
     * @since 2020-11-14
     * @param token 令牌
     * @param path URL路径
     * @return Result风格
     */
    Result getExit(String token, String path);

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
    Result getUpload(String token, String buddha, String name, Boolean sex, String birthday, String city, String signature, String path);

    /**
     * 发送邮箱验证码
     * @author HCY
     * @since 2020-11-15
     * @param email 邮箱
     * @param path URL路径
     * @return Result风格
     */
    Result getVerification(String email, String path);

    /**
     * 判断邮箱是否重复
     * @author HCY
     * @since 2020-11-15
     * @param email 邮箱
     * @param path URL路径
     * @return Result风格
     */
    Result getEmailRepeat(String email,String path);

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
    Result getRegister(String email, String code, String password, String path);

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
    Result getChangePassword(String token, String oldPassword, String newPassword, String path);

    /**
     * 进行关注处理
     * @author HCY
     * @since 2020-11-16
     * @param token 令牌
     * @param focusEmail 关注者邮箱
     * @param path URL路径
     * @return Result风格
     */
    Result getFocus(String token, String focusEmail, String path);

    /**
     * 进行点赞操作,如果已经点赞过了,进行取消
     * @author HCY
     * @since 2020-11-19
     * @param token 令牌
     * @param thumbUpEmail 进行点赞的邮箱
     * @param path URL路径
     * @return Result风格
     */
    Result getLike(String token, String thumbUpEmail, String path);
    /**
    * 查看点赞的用户
    * @author HCY
    * @since 2020/11/20 下午 05:19
    * @param token: 令牌
    * @param path: URL路径
    * @return com.inet.code.utlis.Result
    */
    Result getShowThump(String token, String path);

    /**
    * 查看自己的粉丝
    * @author HCY
    * @since 2020/11/20 下午 07:44
    * @param token: 令牌
    * @param path:  URL路径
    * @return com.inet.code.utlis.Result
    */
    Result getCheckFan(String token, String path);
}
