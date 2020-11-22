package com.inet.code.realize.Impl;

import com.inet.code.entity.Label;
import com.inet.code.realize.AdminBaseService;
import com.inet.code.service.LabelService;
import com.inet.code.utlis.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * AdminBaseServiceImpl
 *
 * @author HCY
 * @since 2020/11/22 下午 09:23
 */
@Service
public class AdminBaseServiceImpl implements AdminBaseService {

    @Resource
    private LabelService labelService;

    /**
     * 新增类别
     * @author HCY
     * @since 2020/11/22 下午 09:28
     * @param labelName: 类别名称
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
     */
    @Override
    public Result getAppendLabel(String labelName, String path) {
        //创建类别的实体类
        Label label = new Label();
        //设置类别名称
        label.setLabelName(labelName);
        //设置创建时间和修改时间
        label.setLabelCreation(new Date());
        label.setLabelModification(new Date());
        //进行存储
        if (labelService.save(label)) {
            return new Result().result200( label+ "类别,新增成功",path);
        }
        return new Result().result500( label+ "类别,新增失败",path);
    }
}
