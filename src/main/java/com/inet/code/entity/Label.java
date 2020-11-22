package com.inet.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理和查看所有的标签
 * </p>
 *
 * @author HCY
 * @since 2020-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_label")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签的uuid
     */
    @TableId(value = "label_uuid",type = IdType.UUID)
    private String labelUuid;

    /**
     * 标签的名字
     */
    @TableField(value = "label_name")
    private String labelName;

    /**
     * 创建时间
     */
    @TableField(value = "label_creation")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime labelCreation;

    /**
     * 修改时间
     */
    @TableField(value = "label_modification")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime labelModification;


}
