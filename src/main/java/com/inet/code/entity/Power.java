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
@TableName("tbl_power")
public class Power implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限uuid
     */
    @TableId(value = "power_uuid",type = IdType.UUID)
    private String powerUuid;

    /**
     * 用户邮箱
     */
    @TableField(value = "power_email")
    private String powerEmail;

    /**
     * 权限uuid
     */
    @TableField(value = "power_role")
    private String powerRole;

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


}
