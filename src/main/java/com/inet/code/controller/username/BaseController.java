package com.inet.code.controller.username;

import com.inet.code.service.UserService;
import com.inet.code.utlis.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * BaseController
 * 基础的用户控制层
 * @author HCY
 * @since 2020/11/14
 */
@RestController
@RequestMapping("/userBased")
@Api(tags = {"用户页面的基础操作"},description = "用户模块")
public class BaseController {
    @Resource
    private UserService userService;

    @PutMapping("/upload")
    public Result putUpload(@RequestHeader(value = "Token",defaultValue = "") String token,
                            @RequestParam(value = "Buddha",defaultValue = "") String buddha,
                            @RequestParam(value = "Name",defaultValue = "") String name,
                            @RequestParam(value = "Sex",defaultValue = "") Boolean sex,
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
