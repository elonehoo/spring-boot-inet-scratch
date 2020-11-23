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


    /**
     * 修改类别
     * @author HCY
     * @since 2020/11/23 7:58 上午
     * @param labelUuid: 标签的序号
     * @param labelName: 标签的名字
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
     */
    @Override
    public Result getAmendLabel(String labelUuid, String labelName, String path) {
        //通过uuid寻找到需要修改的标签
        Label entity = labelService.getById(labelUuid);
        String entityLabelName = entity.getLabelName();
        //判断标签是否真是存在
        if (entity == null){
            return new Result().result404("尚未找到需要修改的标签",path);
        }
        //修改标签的名字
        entity.setLabelName(labelName);
        //修改标签的修改时间
        entity.setLabelModification(new Date());
        //进行修改
        if (labelService.updateById(entity)) {
            return new Result().result200(
                    "修改成功,成功将" + entityLabelName + "修改成为" +labelName
                    ,path);
        }
        return new Result().result200(
                "修改失败,未将" + entityLabelName + "修改成为" +labelName
                ,path);
    }
}
