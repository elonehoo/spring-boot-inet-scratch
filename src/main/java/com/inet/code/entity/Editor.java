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
 * 管理一个文件的类别
 * </p>
 *
 * @author HCY
 * @since 2020-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_editor")
public class Editor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签管理器的uuid
     */
    @TableId(value = "editor_uuid",type = IdType.UUID)
    private String editorUuid;

    /**
     * 文件的uuid
     */
    @TableField(value = "editor_matter_uuid")
    private String editorMatterUuid;

    /**
     * 标签的uuid
     */
    @TableField(value = "editor_label_uuid")
    private String editorLabelUuid;

    /**
     * 创建时间
     */
    @TableField(value = "editor_creation")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date editorCreation;

    /**
     * 修改时间
     */
    @TableField(value = "editor_modification")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date editorModification;


}
