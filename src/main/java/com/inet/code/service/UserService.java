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
}
