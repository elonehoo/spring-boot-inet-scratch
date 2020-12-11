package com.inet.code.realize.Impl;

import com.inet.code.entity.Label;
import com.inet.code.entity.Type;
import com.inet.code.realize.AdminBaseService;
import com.inet.code.service.EditorService;
import com.inet.code.service.LabelService;
import com.inet.code.service.TypeService;
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

    @Resource
    private EditorService editorService;

    @Resource
    private TypeService typeService;

    /**
     * 新增标签
     * @author HCY
     * @since 2020/11/22 下午 09:28
     * @param labelName: 标签名称
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
//        label.setLabelCreation(new Date());
//        label.setLabelModification(new Date());
        //进行存储
        if (labelService.save(label)) {
            return new Result().result200( labelName + "标签,新增成功",path);
        }
        return new Result().result500( labelName + "标签,新增失败",path);
    }

    /**
     * 修改标签
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
//        entity.setLabelModification(new Date());
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

    /**
     * 通过标签的uuid删除标签
     * @author HCY
     * @since 2020/11/23 11:16 下午
     * @param labelUuid: 标签的uuid
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
     */
    @Override
    public Result removePremiseLabel(String labelUuid, String path) {
        //查询有多少条记录是属于这个类别的
        Integer count = editorService.getByLabelUuidCount(labelUuid);
        //通过主键进行查询到类别的名字
        Label label = labelService.getById(labelUuid);
        //提示返回值
        return new Result().result200(
                label.getLabelName() + "的标签有" + count + "个项目，您确认需要删除吗？"
                ,path);
    }

    /**
     * 通过标签的uuid删除标签(删除操作)
     * 需要删除所有属于该标签的名字
     * @author HCY
     * @since 2020/11/24 9:00 上午
     * @param labelUuid: 标签的uuid
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
     */
    @Override
    public Result removeLabel(String labelUuid, String path) {
        //通过uuid查找到该类别
        Label label = labelService.getById(labelUuid);
        //判断类别是否从label表中删除
        if (labelService.removeById(labelUuid)) {
            //删除属于该类别的项目文件
            if (editorService.removeByLabelUuid(labelUuid)) {
                return new Result().result200("删除成功！",path);
            }
            //删除失败的情况
            //将数据回滚
            labelService.save(label);
            //返回值
            return new Result().result500("删除失败！",path);
        }
        //删除失败
        return new Result().result500("删除失败！",path);
    }

    /**
     * 新增类别
     * @author HCY
     * @since 2020/11/24 9:52 上午
     * @param typeName: 类别名称
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
     */
    @Override
    public Result getAppendType(String typeName, String path) {
        //创建实体类
        Type type = new Type();
        //设置名字
        type.setTypeName(typeName);
        //设置创建时间和修改时间
//        type.setTypeCreation(new Date());
//        type.setTypeModification(new Date());
        //进行存储操作
        if (typeService.save(type)) {
            return new Result().result200(
                    "新增" + typeName + "类别成功"
                    ,path);
        }
        return new Result().result500("新增" + typeName + "类别失败",path);
    }

    /**
     * 通过类别的uuid查找到类别实体类，进行修改
     * @author HCY
     * @since 2020/11/24 10:51 上午
     * @param typeUuid: 类别序号
     * @param typeName: 类别名字
     * @param path: URL路径
     * @return com.inet.code.utlis.Result
     */
    @Override
    public Result getAmendType(String typeUuid, String typeName, String path) {
        //通过类别序号查找类别尸体
        Type type = typeService.getById(typeUuid);
        //获取类别的名称
        String name = type.getTypeName();
        //进行修改类别名称
        type.setTypeName(typeName);
        //进行修改时间
//        type.setTypeModification(new Date());
        if (typeService.updateById(type)) {
            return new Result().result200(
                    "成功将" + name + "修改成" + typeName
                    ,path);
        }
        return new Result().result200(
                "未能将" + name + "修改成" + typeName
                ,path);
    }
}
