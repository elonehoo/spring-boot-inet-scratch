package com.inet.code.controller.administrator;

import com.inet.code.realize.AdminBaseService;
import com.inet.code.service.UserService;
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
    @PutMapping("/amendLabel")
    public Result putAmendLabel(@RequestParam(value = "LabelUuid",defaultValue = "") String labelUuid,
                                @RequestParam(value = "LabelName",defaultValue = "") String labelName){
        return adminBaseService.getAmendLabel(
                 labelUuid
                ,labelName
                ,"/scratch/adminBased/amendLabel");
    }

}
