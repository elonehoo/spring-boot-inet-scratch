package com.inet.code.controller.username;

import com.inet.code.service.UserService;
import com.inet.code.utlis.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * BaseController
 * 基础的用户控制层
 * @author HCY
 * @since 2020/11/14
 */
@RestController
@CrossOrigin
@RequestMapping("/userBased")
@Api(tags = {"用户页面的基础操作"},description = "用户模块")
public class UserBasedController {
    @Resource
    private UserService userService;

    /**
     * 用户进行信息的修改
     * @author HCY
     * @since 2020-11-14
     * @param token 令牌
     * @param buddha 头像
     * @param name 名字
     * @param sex 性别
     * @param birthday 生日
     * @param city 地址
     * @param signature 个性签名
     * @return Result
     */
    @ApiOperation("修改操作")
    @RequiresRoles(value = {"member"})
    @ApiImplicitParams({
            @ApiImplicitParam(name="Token",value="令牌",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="Buddha",value="头像",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="Name",value="名字",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="Sex",value="性别",dataType="Boolean", paramType = "query"),
            @ApiImplicitParam(name="Birthday",value="生日",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="City",value="生日",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="Signature",value="个性签名",dataType="String", paramType = "query"),
    })
    @PutMapping("/upload")
    public Result putUpload(@RequestHeader(value = "Token",defaultValue = "") String token,
                            @RequestParam(value = "Buddha",defaultValue = "") String buddha,
                            @RequestParam(value = "Name",defaultValue = "") String name,
                            @RequestParam(value = "Sex",defaultValue = "true") Boolean sex,
                            @RequestParam(value = "Birthday",defaultValue = "") String birthday,
                            @RequestParam(value = "City",defaultValue = "") String city,
                            @RequestParam(value = "Signature",defaultValue = "") String signature){
        return userService.getUpload(
                token
                ,buddha
                ,name
                ,sex
                ,birthday
                ,city
                ,signature
                ,"scratch/userBased/upload");
    }

}
