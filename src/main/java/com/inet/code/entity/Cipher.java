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
 * <p>
 * 
 * </p>
 *
 * @author HCY
 * @since 2020-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_cipher")
public class Cipher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 密码实体类的uuid
     */
    @TableId(value = "cipher_uuid",type = IdType.UUID)
    private String cipherUuid;

    /**
     * 用户账号(邮箱)
     */
    @TableField(value = "cipher_email")
    private String cipherEmail;

    /**
     * 用户密码(MD5加密)
     */
    @TableField(value = "cipher_password")
    private String cipherPassword;

    /**
     * 创建时间
     */
    @TableField(value = "cipher_creation",update = "NOW()",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cipherCreation;

    /**
     * 修改时间
     */
    @TableField(value = "cipher_modification",update = "NOW()",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cipherModification;


}
