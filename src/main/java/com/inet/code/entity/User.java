package com.inet.code.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户实体类
 * @author HCY
 * @since 2020-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户的uuid
     */
    @TableId(value = "user_uuid",type = IdType.UUID)
    private String userUuid;

    /**
     * 头像的连接地址
     */
    @TableField(value = "user_buddha")
    private String userBuddha;

    /**
     * 用户昵称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户邮箱
     */
    @TableField(value = "user_email")
    private String userEmail;

    /**
     * 用户性别
     */
    @TableField(value = "user_sex")
    private Boolean userSex;

    /**
     * 用户生日
     */
    @TableField(value = "user_birthday")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date userBirthday;

    /**
     * 用户所在城市
     */
    @TableField(value = "user_city")
    private String userCity;

    /**
     * 用户的个性签名
     */
    @TableField(value = "user_signature")
    private String userSignature;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

    /**
     * 权限名称
     */
    @TableField(exist = false)
    private String roleName;

    /**
    * 点赞时间
    */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date likeTime;

}
