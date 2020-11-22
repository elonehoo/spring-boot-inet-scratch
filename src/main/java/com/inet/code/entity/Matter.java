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
 * 上传的文件表,一般只存储sb3
 * </p>
 *
 * @author HCY
 * @since 2020-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tbl_matter")
public class Matter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件的序号
     */
    @TableId(value = "matter_uuid",type = IdType.UUID)
    private String matterUuid;

    /**
     * 文件的名字
     */
    @TableField(value = "matter_name")
    private String matterName;

    /**
     * 文件的网络地址
     */
    @TableField(value = "matter_url")
    private String matterUrl;

    /**
     * 文件的存储地址
     */
    @TableField(value = "matter_address")
    private String matterAddress;

    /**
     * 用户的邮箱
     */
    @TableField(value = "matter_user_email")
    private String matterUserEmail;

    /**
     * 是否发布
     */
    @TableField(value = "matter_issue")
    private Boolean matterIssue;

    /**
     * 类型的uuid
     */
    @TableField(value = "matter_type_uuid")
    private String matterTypeUuid;

    /**
     * 是否允许改编
     */
    @TableField(value = "matter_adapt")
    private Boolean matterAdapt;

    /**
     * 创建时间
     */
    @TableField(value = "matter_creation")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime matterCreation;

    /**
     * 修改时间
     */
    @TableField(value = "matter_modification")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime matterModification;


}
