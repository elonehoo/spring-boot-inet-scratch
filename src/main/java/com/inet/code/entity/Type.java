package com.inet.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 类别的管理器
 * </p>
 *
 * @author HCY
 * @since 2020-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_type")
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类别的uuid
     */
    @TableId(value = "type_uuid",type = IdType.UUID)
    private String typeUuid;

    /**
     * 类别的名称
     */
    @TableField(value = "type_name")
    private String typeName;

    /**
     * 创建时间
     */
    @TableField(value = "type_creation")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date typeCreation;

    /**
     * 修改时间
     */
    @TableField(value = "type_modification")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date typeModification;


}
