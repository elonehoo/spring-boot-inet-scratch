package com.inet.code.mapper;

import com.inet.code.entity.Editor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 管理一个文件的类别 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2020-11-22
 */
public interface EditorMapper extends BaseMapper<Editor> {

    /**
     * 查询有多少条属于这个类别的项目文件
     * @author HCY
     * @since 2020/11/23 11:26 下午
     * @param labelUuid: 类别序号
     * @return java.lang.Integer
     */
    Integer getLabelUuidCount(String labelUuid);

    /**
     * 通过标签的序号删除属于该标签的项目文件
     * @author HCY
     * @since 2020/11/24 9:03 上午
     * @param labelUuid: 标签序号
     * @return java.lang.Boolean
     */
    Boolean removeByLabelUuid(String labelUuid);
}
