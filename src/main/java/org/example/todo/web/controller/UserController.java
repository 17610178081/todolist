package org.example.todo.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.example.todo.domain.entity.User;
import org.example.todo.domain.service.UserService;
import org.example.todo.util.Code;
import org.example.todo.util.MD5;
import org.example.todo.util.ResultJson;
import org.example.todo.util.UserToken;
import org.example.todo.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "用户相关接口", description = "提供用户相关 Rest API")
public class UserController {

    @Autowired
    private UserService userService;//用户service
    ;/** @Author 好栖君(lyj)
     * @Description 登录
     * @Date 14:42 2021/8/12
     * @Param
     * @return
     **/
    @PostMapping("/login")
    @ApiOperation("登录")
    public String login(@RequestBody @Validated LoginVo model){
        ResultJson retJson = new ResultJson(0, Code.C0000, "", "");
        try {
            if (model == null) {
                retJson.setCode(Code.C2002);
                retJson.setInfo("请求数据不能为空！");
                return JSONObject.fromObject(retJson).toString();
            }
            if(StringUtils.isBlank(model.getUsername())){
                retJson.setCode(Code.C2003);
                retJson.setInfo("用户名不能为空！");
                return JSONObject.fromObject(retJson).toString();
            }
            if(StringUtils.isBlank(model.getPassword())){
                retJson.setCode(Code.C2003);
                retJson.setInfo("密码不能为空！");
                return JSONObject.fromObject(retJson).toString();
            }
            User user=userService.findByUserName(model.getUsername());
            if(user==null){
                retJson.setStatus(1);
                retJson.setCode(Code.C2005);
                retJson.setInfo("账号不存在！");
                return JSONObject.fromObject(retJson).toString();
            }
            if (user.getStatus() == 0) {
                retJson.setCode(Code.C2009);
                retJson.setInfo("该账号已被冻结，不能登录，请联系管理人员！");
                return JSONObject.fromObject(retJson).toString();
            }
            boolean b= MD5.validatePassword(user.getPassWord(),model.getPassword());
            if(!b){
                retJson.setStatus(1);
                retJson.setCode(Code.C2008);
                retJson.setInfo("用户名密码不匹配！");
                return JSONObject.fromObject(retJson).toString();
            }
            user.setTokenValue(UserToken.makeToken(user.getUserName()));
            userService.update(user);
            JSONObject jj=new JSONObject();
            jj.put("id",user.getId());
            jj.put("token",user.getTokenValue());
            retJson.setData(jj);
            retJson.setStatus(1);
            retJson.setCode(Code.C1111);
            retJson.setInfo("操作成功！");
       }catch (Exception e){
            e.printStackTrace();
       }
        return JSONObject.fromObject(retJson).toString();
    }
    /** @Author 好栖君(lyj)
     * @Description //退出登录
     * @Date 16:18 2021/8/12
     * @Param
     * @return
     **/
    @GetMapping("/login_out/{id}/{token}")
    @ApiOperation("退出登录")
    public String loginOut(@PathVariable(name = "id")  Long id,@PathVariable(name = "token")  String token) {
        ResultJson retJson = new ResultJson(0, Code.C0000, "", "");
       try {
           if(id==null){
               retJson.setCode(Code.C2003);
               retJson.setInfo("id不能为空！");
               return JSONObject.fromObject(retJson).toString();
           }
           if(StringUtils.isNotBlank(token)){
               retJson.setCode(Code.C2003);
               retJson.setInfo("token不能为空！");
               return JSONObject.fromObject(retJson).toString();
           }
           User user=userService.findById(id);
           if(user==null){
               retJson.setStatus(1);
               retJson.setCode(Code.C2005);
               retJson.setInfo("账号不存在！");
               return JSONObject.fromObject(retJson).toString();
           }
           user.setTokenValue(UserToken.makeToken(user.getUserName()));
           userService.update(user);
           retJson.setStatus(1);
           retJson.setCode(Code.C1111);
           retJson.setInfo("操作成功！");
       }catch (Exception e){
           e.printStackTrace();
       }
        return JSONObject.fromObject(retJson).toString();
    }
}
