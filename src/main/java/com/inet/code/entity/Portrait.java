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
 * 默认头像
 * </p>
 *
 * @author HCY
 * @since 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_portrait")
public class Portrait implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认头像的uuid
     */
    @TableId(value = "portrait_uuid",type = IdType.UUID)
    private String portraitUuid;

    /**
     * 默认头像的SRC地址
     */
    @TableField(value = "portrait_src")
    private String portraitSrc;

    /**
     * 默认头像的创建时间
     */
    @TableField(value = "portrait_creation")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date portraitCreation;

    /**
     * 默认头像的修改时间
     */
    @TableField(value = "portrait_modification",update = "NOW()",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date portraitModification;


}
