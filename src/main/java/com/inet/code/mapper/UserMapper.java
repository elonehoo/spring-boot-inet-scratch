package com.inet.code.mapper;

import com.inet.code.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-11-14
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 登录操作
     * @author HCY
     * @since 2020-11-14
     * @param account 账号
     * @param password 密码
     * @return User实体类
     */
    User getLogin(String account, String password);

    /**
     * 判断邮箱是否重复
     * @param email 邮箱
     * @return User实体类
     */
    User getEMailRepeat(String email);

    /**
     * 通过邮箱查找用户，一般用户判断关注或者点赞的用户是否存在
     * @author HCY
     * @since 2020-11-17
     * @param focusEmail 关注者邮箱
     * @return User实体类
     */
    User getByEmail(String focusEmail);

    /**
    * 查看关注自己的人
    * @author HCY
    * @since 2020/11/20 下午 05:52
    * @param userEmail: 用户邮箱
    * @return java.util.List<com.inet.code.entity.User>
    */
    List<User> getShowThump(String userEmail);

    /**
    * 查看自己的粉丝
    * @author HCY
    * @since 2020/11/20 下午 07:55
    * @param userEmail: 用户的邮箱
    * @return java.util.List<com.inet.code.entity.User>
    */
    List<User> getCheckFan(String userEmail);
}
