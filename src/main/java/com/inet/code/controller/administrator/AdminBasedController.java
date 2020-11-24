package com.inet.code.controller.administrator;

import com.inet.code.realize.AdminBaseService;
import com.inet.code.utlis.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * AdminBase
 * 基础的管理控制层
 * @author HCY
 * @since 2020/11/14
 */
@RestController
@CrossOrigin
@RequestMapping("/adminBased")
@Api(tags = {"管理页面的基础操作"},description = "管理模块")
public class AdminBasedController {

    @Resource
    private AdminBaseService adminBaseService;

    /**
    * 完成标签的添加
    * @author HCY
    * @since 2020/11/22 下午 09:07
    * @param labelName: 标签的名称
    * @return com.inet.code.utlis.Result
    */
    @ApiOperation("新增标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name="LabelName",value="标签的名称",dataType="String", paramType = "query"),
    })
    @PostMapping("/appendLabel")
    @RequiresRoles(value = {"admin"})
    public Result postAppendLabel(@RequestParam(value = "LabelName",defaultValue = "") String labelName){
        return adminBaseService.getAppendLabel(labelName,"/scratch/adminBased/appendLabel");
    }

    /**
     * 完成标签的修改
     * @author HCY
     * @since 2020/11/23 8:08 上午
     * @param labelUuid: 标签的uuid
     * @param labelName: 新的标签名字
     * @return com.inet.code.utlis.Result
    */
    @ApiOperation("标签的修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name="LabelUuid",value="标签的uuid",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="LabelName",value="标签的名称",dataType="String", paramType = "query"),
    })
    @PutMapping("/amendLabel")
    @RequiresRoles(value = {"admin"})
    public Result putAmendLabel(@RequestParam(value = "LabelUuid",defaultValue = "") String labelUuid,
                                @RequestParam(value = "LabelName",defaultValue = "") String labelName){
        return adminBaseService.getAmendLabel(
                 labelUuid
                ,labelName
                ,"/scratch/adminBased/amendLabel");
    }

    /**
     * 通过标签的uuid查询到有多少个项目文件属于这个标签
     * @author HCY
     * @since 2020/11/24 7:56 上午
     * @param labelUuid: 标签的uuid
     * @return com.inet.code.utlis.Result
    */
    @ApiOperation("通过标签的uuid查询到有多少个项目文件属于这个标签(删除前先调用这个方法)")
    @ApiImplicitParams({
            @ApiImplicitParam(name="LabelUuid",value="标签的uuid",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="LabelName",value="标签的名称",dataType="String", paramType = "query"),
    })
    @GetMapping("/removePremiseLabel")
    @RequiresRoles(value = {"admin"})
    public Result getRemoveLabel(@RequestParam(value = "LabelUuid",defaultValue = "") String labelUuid){
        return adminBaseService.removePremiseLabel(
                labelUuid
                ,"/scratch/adminBased/removePremiseLabel");
    }

    /**
     * 删除标签
     * @author HCY
     * @since 2020/11/24 9:22 上午
     * @param labelUuid:标签的uuid
     * @return com.inet.code.utlis.Result
    */
    @ApiOperation("删除请求，会删除所有属于该标签的所有项目文件的这个标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name="LabelUuid",value="标签的uuid",dataType="String", paramType = "query"),
    })
    @DeleteMapping("/removeLabel")
    @RequiresRoles(value = {"admin"})
    public Result deleteRemoveLabel(@RequestParam(value = "LabelUuid",defaultValue = "") String labelUuid){
        return adminBaseService.removeLabel(
                labelUuid
                ,"/scratch/adminBased/removeLabel");
    }

    /**
     * 新增类别
     * @author HCY
     * @since 2020/11/24 10:27 上午
     * @param typeName: 类别名称
     * @return com.inet.code.utlis.Result
    */
    @ApiOperation("添加新的类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name="TypeName",value="类别的名称",dataType="String", paramType = "query"),
    })
    @PostMapping("/appendType")
    @RequiresRoles(value = {"admin"})
    public Result postAppendType(@RequestParam(value = "TypeName",defaultValue = "") String typeName){
        return adminBaseService.getAppendType(
                typeName
                ,"/scratch/adminBased/appendType");
    }

    /**
    * 类别的修改
    * @author HCY
    * @since 2020/11/24 下午 08:34
    * @param typeUuid: 类别的主键
    * @param typeName: 类别的名字
    * @return com.inet.code.utlis.Result
    */
    @ApiOperation("类别的修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name="TypeUuid",value="类别的uuid",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="TypeName",value="类别的名称",dataType="String", paramType = "query"),
    })
    @PutMapping("/amendType")
    @RequiresRoles(value = {"admin"})
    public Result getAmendType(@RequestParam(value = "TypeUuid",defaultValue = "") String typeUuid,
                               @RequestParam(value = "TypeName",defaultValue = "") String typeName){
        return adminBaseService.getAmendType(
                 typeUuid
                ,typeName
                ,"/scratch/adminBased/amendType");
    }

}
