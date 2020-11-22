package com.inet.code.realize;

import com.inet.code.utlis.Result;

/**
* 基础功能
* @author HCY
* @since 2020/11/22 下午 09:19
*/
public interface BaseService {
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
}
