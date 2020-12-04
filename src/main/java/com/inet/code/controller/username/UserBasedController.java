package com.inet.code.controller.username;

import com.inet.code.realize.UserBaseService;
import com.inet.code.service.UserService;
import com.inet.code.utlis.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
    private UserBaseService userBaseService;

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
        return userBaseService.getUpload(
                 token
                ,buddha
                ,name
                ,sex
                ,birthday
                ,city
                ,signature
                ,"/scratch/userBased/upload");
    }

    /**
     * 通过邮箱发送验证码
     * @author HCY
     * @since 2020-11-16
     * @param email 邮箱
     * @return Result
     */
    @ApiOperation("通过邮箱发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="Email",value="邮箱",dataType="String", paramType = "query"),
    })
    @GetMapping("/verification")
    public Result getVerification(@RequestParam(value = "Email",defaultValue = "") String email){
        return userBaseService.getVerification(
                email
                ,"/scratch/userBased/upload");
    }

    /**
     * 完成验证码之后的注册请求
     * @author HCY
     * @since 2020-11-16
     * @param email 邮箱
     * @param code 验证码
     * @param password 密码
     * @return Result
     */
    @ApiOperation("完成验证码之后的注册请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name="Email",value="邮箱",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="Code",value="验证码",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="Password",value="密码",dataType="String", paramType = "query"),
    })
    @PostMapping("/register")
    public Result postRegister(@RequestParam(value = "Email",defaultValue = "") String email,
                               @RequestParam(value = "Code",defaultValue = "") String code,
                               @RequestParam(value = "Password",defaultValue = "") String password){
        return userBaseService.getRegister(
                email
                ,code
                ,password
                ,"/scratch/userBased/register");
    }

    /**
     * 修改密码操作
     * @author HCY
     * @since 2020-11-16
     * @param token 令牌
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return Result
     */
    @ApiOperation("进行密码的修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name="OldPassword",value="旧密码",dataType="String", paramType = "query"),
            @ApiImplicitParam(name="NewPassword",value="新密码",dataType="String", paramType = "query"),
    })
    @RequiresRoles(value = {"member"})
    @PutMapping("/change")
    public Result putChange(@RequestHeader(value = "Token",defaultValue = "") String token,
                            @RequestParam(value = "OldPassword",defaultValue = "") String oldPassword,
                            @RequestParam(value = "NewPassword",defaultValue = "") String newPassword){
        return userBaseService.getChangePassword(
                token
                ,oldPassword
                ,newPassword
                ,"/scratch/userBased/register");
    }

    /**
     * 关注某一个用户,如果已经关注过了,则进行取消关注操作
     * @author HCY
     * @since 2020-11-17
     * @param token 令牌
     * @param focusEmail 关注的邮箱
     * @return Result
     */
    @ApiOperation("关注某一个用户,如果已经关注过了,则进行取消操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name="FocusEmail",value="关注的邮箱",dataType="String", paramType = "query"),
    })
    @RequiresRoles(value = {"member"})
    @PutMapping("/focus")
    public Result putFocus(@RequestHeader(value = "Token",defaultValue = "") String token,
                           @RequestParam(value = "FocusEmail",defaultValue = "") String focusEmail){
        return userBaseService.getFocus(
                token
                ,focusEmail
                ,"scratch/userBased/register");
    }

    /**
     * 点赞某一个用户,如果已经点赞过了,则进行取消操作
     * @param token 令牌
     * @param thumbUpEmail 需要点赞的用户邮箱
     * @return Result
     */
    @ApiOperation("点赞某一个用户,如果已经点赞过了,则进行取消操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ThumbUpEmail",value="点赞的邮箱",dataType="String", paramType = "query"),
    })
    @RequiresRoles(value = {"member"})
    @GetMapping("/like")
    public Result getLikes(@RequestHeader(value = "Token",defaultValue = "") String token,
                           @RequestParam(value = "ThumbUpEmail",defaultValue = "") String thumbUpEmail){
        return userBaseService.getLike(
                  token
                , thumbUpEmail
                , "scratch/userBased/like");
    }

    /**
    * 查看给自己点赞的用户
    * @author HCY
    * @since 2020/11/20 下午 05:18
    * @param token: 令牌
    * @return com.inet.code.utlis.Result
    */
    @ApiOperation("查看给自己点赞的用户")
    @GetMapping("/showThump")
    @RequiresRoles(value = {"member"})
    public Result getShowThump(@RequestHeader(value = "Token",defaultValue = "") String token){
        return userBaseService.getShowThump(
                 token
                ,"scratch/userBased/showThump");
    }

    /**
    * 查看自己的粉丝
    * @author HCY
    * @since 2020/11/20 下午 08:03
    * @param token: 令牌
    * @return com.inet.code.utlis.Result
    */
    @ApiOperation("查看自己的粉丝")
    @GetMapping("/checkFan")
    @RequiresRoles(value = {"member"})
    public Result getCheckFan(@RequestHeader(value = "Token",defaultValue = "") String token){
        return userBaseService.getCheckFan(token,"scratch/userBased/checkFan");
    }

    /**
    * 上传.SB3文件,返回文件的URL地址
    * @author HCY
    * @since 2020/11/22 下午 08:18
    * @param file: SB3 的文件
    * @return com.inet.code.utlis.Result
    */
    @ApiOperation("上传sb3文件,返回文件的URL地址")
    @PostMapping(value = "/uploading",headers = "content-type=multipart/form-data")
    @RequiresRoles(value = {"member"})
    public Result postUploading(@RequestParam(value = "file") @RequestPart MultipartFile file){
        return userBaseService.getUploading(
                 file
                ,"scratch/userBased/uploading");
    }

}
