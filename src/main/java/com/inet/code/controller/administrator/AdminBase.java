package com.inet.code.controller.administrator;

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
@RequestMapping("/adminBased")
@Api(tags = {"管理页面的基础操作"},description = "管理模块")
public class AdminBase {

    @Resource
    private UserService userService;

    /**
     * 登录操作
     * @author HCY
     * @since 2020-11-14
     * @param account 账号等同于邮箱
     * @param password 密码
     * @return Result风格的返回值
     */
    @ApiOperation("登录操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name="Account",value="账号等同于邮箱",dataType="String", paramType = "query",example=""),
            @ApiImplicitParam(name="Password",value="密码",dataType="String", paramType = "query",example=""),
    })
    @PostMapping("/login")
    public Result getLogin(@RequestParam("Account") String account,
                          @RequestParam("Password") String password){
        return userService.getLogin(account,password,"scratch/adminBased/login");
    }

    @GetMapping("/test")
    @RequiresRoles(value = {"admin"})
    public String getTest(){
        return "ADMIN";
    }


}
