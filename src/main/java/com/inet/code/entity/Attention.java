package com.inet.code.entity;

import com.baomidou.mybatisplus.annotation.*;

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
@TableName("tbl_attention")
public class Attention implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关注的uuid
     */
    @TableId(value = "attention_uuid",type = IdType.UUID)
    private String attentionUuid;

    /**
     * 用户的邮箱
     */
    @TableField(value = "attention_email")
    private String attentionEmail;

    /**
     * 用户的关注者的邮箱
     */
    @TableField(value = "attention_concern")
    private String attentionConcern;

    /**
     * 创建时间
     */
    @TableField(value = "attention_creation")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date attentionCreation;

    /**
     * 修改时间
     */
    @TableField(value = "attention_modification",update = "NOW()")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date attentionModification;


}
