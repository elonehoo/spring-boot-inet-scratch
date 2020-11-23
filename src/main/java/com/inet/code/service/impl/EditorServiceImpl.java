package com.inet.code.service.impl;

import com.inet.code.entity.Editor;
import com.inet.code.mapper.EditorMapper;
import com.inet.code.service.EditorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 管理一个文件的类别 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2020-11-22
 */
@Service
public class EditorServiceImpl extends ServiceImpl<EditorMapper, Editor> implements EditorService {

    @Resource
    private EditorMapper editorMapper;

    /**
     * 查询有多少条属于这个类别的项目文件
     * @author HCY
     * @since 2020/11/23 11:26 下午
     * @param labelUuid: 类别序号
     * @return java.lang.Integer
     */
    @Override
    public Integer getByLabelUuidCount(String labelUuid) {
        return editorMapper.getLabelUuidCount(labelUuid);
    }
}
