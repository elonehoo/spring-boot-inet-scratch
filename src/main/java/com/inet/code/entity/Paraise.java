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
@TableName("tbl_paraise")
public class Paraise implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 赞uuid
     */
    @TableId(value = "praise_uuid",type = IdType.UUID)
    private String praiseUuid;

    /**
     * 用户邮箱
     */
    @TableField(value = "praise_email")
    private String praiseEmail;

    /**
     * 点赞的用户邮箱
     */
    @TableField(value = "praise_economy")
    private String praiseEconomy;

    /**
     * 创建时间
     */
    @TableField(value = "praise_creation")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date praiseCreation;

    /**
     * 修改时间
     */
    @TableField(value = "praise_modification",update = "NOW()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date praiseModification;


}
