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
@Api("用户页面的基础操作")
public class BaseController {
    @Resource
    private UserService userService;



}
