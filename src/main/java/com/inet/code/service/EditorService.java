package com.inet.code.service;

import com.inet.code.entity.Editor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理一个文件的类别 服务类
 * </p>
 *
 * @author HCY
 * @since 2020-11-22
 */
public interface EditorService extends IService<Editor> {

    /**
     * 查询有多少条属于这个类别的项目文件
     * @author HCY
     * @since 2020/11/23 11:26 下午
     * @param labelUuid: 类别序号
     * @return java.lang.Integer
    */
    Integer getByLabelUuidCount(String labelUuid);

}
