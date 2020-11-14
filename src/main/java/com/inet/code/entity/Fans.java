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
@TableName("tbl_fans")
public class Fans implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 粉丝uuid
     */
    @TableId(value = "fans_uuid",type = IdType.UUID)
    private String fansUuid;

    /**
     * 用户的邮箱
     */
    @TableField(value = "fans_email")
    private String fansEmail;

    /**
     * 关注用户的粉丝邮箱
     */
    @TableField(value = "fans_economy")
    private String fansEconomy;

    /**
     * 创建时间
     */
    @TableField(value = "fans_creation")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date fansCreation;

    /**
     * 修改时间
     */
    @TableField(value = "fans_modification",update = "NOW()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date fansModification;


}
