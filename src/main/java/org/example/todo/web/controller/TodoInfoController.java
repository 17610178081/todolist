package org.example.todo.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.example.todo.domain.entity.ToInfo;
import org.example.todo.domain.service.TodoInfoService;
import org.example.todo.domain.service.UserService;
import org.example.todo.util.Code;
import org.example.todo.util.ResultJson;
import org.example.todo.vo.ToInfoVo;
import org.example.todo.vo.TodoInfoPageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/todo_info")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "代办事项相关接口", description = "提供待办事项相关 Rest API")
public class TodoInfoController {
    @Autowired
    private UserService userService;//用户service
    @Autowired
    private TodoInfoService todoInfoService;//代办事项service

     /** @Author 好栖君(lyj)
      * @Description //待办事项保存
      * @Date 23:00 2021/8/12
      * @Param
      * @return
      **/
    @PostMapping("/save/{userId}/{token}")
    @ApiOperation("保存")
    public String save(@PathVariable(name = "userId")  Long userId,@PathVariable(name = "token")  String token,@RequestBody @Validated ToInfoVo model){
        ResultJson retJson = new ResultJson(0, Code.C0000, "", "");
        try {
            if(userId==null){
                retJson.setCode(Code.C2003);
                retJson.setInfo("用户id不能为空！");
                return JSONObject.fromObject(retJson).toString();
            }
            if(StringUtils.isBlank(token)){
                retJson.setCode(Code.C2003);
                retJson.setInfo("token不能为空！");
                return JSONObject.fromObject(retJson).toString();
            }
            if (model == null) {
                retJson.setCode(Code.C2002);
                retJson.setInfo("请求数据不能为空！");
                return JSONObject.fromObject(retJson).toString();
            }
            boolean bl=userService.checkUserToken(userId,token);
            if (!bl) {
                retJson.setCode(Code.C2007);
                retJson.setInfo("账号信息失效，请重新登录后再尝试操作！");
                return JSONObject.fromObject(retJson).toString();
            }
            ToInfo toInfo=new ToInfo();
            BeanUtils.copyProperties(model, toInfo);
            toInfo.setUserId(userId);
            toInfo.setCreateTime(LocalDateTime.now());
            todoInfoService.save(toInfo);
            retJson.setData(toInfo);
            retJson.setStatus(1);
            retJson.setCode(Code.C1111);
            retJson.setInfo("操作成功！");
       }catch (Exception e){
            e.printStackTrace();
       }
        return JSONObject.fromObject(retJson).toString();
    }
    /** @Author 好栖君(lyj)
     * @Description //待办事项更新
     * @Date 23:00 2021/8/12
     * @Param
     * @return
     **/
    @PostMapping("/update/{userId}/{token}")
    @ApiOperation("更新")
    public String update(@PathVariable(name = "userId")  Long userId,@PathVariable(name = "token")  String token,@RequestBody @Validated ToInfoVo model){
        ResultJson retJson = new ResultJson(0, Code.C0000, "", "");
        try {
            if(userId==null){
                retJson.setCode(Code.C2003);
                retJson.setInfo("用户id不能为空！");
                return JSONObject.fromObject(retJson).toString();
            }
            if(StringUtils.isBlank(token)){
                retJson.setCode(Code.C2003);
                retJson.setInfo("token不能为空！");
                return JSONObject.fromObject(retJson).toString();
            }
            if (model == null) {
                retJson.setCode(Code.C2002);
                retJson.setInfo("请求数据不能为空！");
                return JSONObject.fromObject(retJson).toString();
            }
            if(model.getId()==null){
                retJson.setCode(Code.C2002);
                retJson.setInfo("代办事项Id不能为空！");
                return JSONObject.fromObject(retJson).toString();
            }
            boolean bl=userService.checkUserToken(userId,token);
            if (!bl) {
                retJson.setCode(Code.C2007);
                retJson.setInfo("账号信息失效，请重新登录后再尝试操作！");
                return JSONObject.fromObject(retJson).toString();
            }
            ToInfo toInfo=todoInfoService.findById(model.getId());
            if(toInfo==null){
                retJson.setStatus(1);
                retJson.setCode(Code.C2005);
                retJson.setInfo("id对应的代办事项信息不存在！");
                return JSONObject.fromObject(retJson).toString();
            }
            if(!userId.equals(toInfo.getUserId())){
                retJson.setStatus(1);
                retJson.setCode(Code.C2007);
                retJson.setInfo("你无权限操作此项记录！");
                return JSONObject.fromObject(retJson).toString();
            }
            BeanUtils.copyProperties(model, toInfo);

            todoInfoService.update(toInfo);
            retJson.setData(toInfo);
            retJson.setStatus(1);
            retJson.setCode(Code.C1111);
            retJson.setInfo("操作成功！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.fromObject(retJson).toString();
    }
    /** @Author 好栖君(lyj)
     * @Description //条件过滤查询、分页
     * @Date 23:00 2021/8/12
     * @Param
     * @return
     **/
    @PostMapping("/find_listpage_by_condition/{userId}/{token}")
    @ApiOperation("条件查询待办事项列表分页")
    public String findListPageByCondition(@PathVariable(name = "userId")  Long userId,@PathVariable(name = "token")  String token,@RequestBody @Validated TodoInfoPageVo model) {
        ResultJson retJson = new ResultJson(0, Code.C0000, "", "");
       try {
           if(userId==null){
               retJson.setCode(Code.C2003);
               retJson.setInfo("用户id不能为空！");
               return JSONObject.fromObject(retJson).toString();
           }
           if(StringUtils.isBlank(token)){
               retJson.setCode(Code.C2003);
               retJson.setInfo("token不能为空！");
               return JSONObject.fromObject(retJson).toString();
           }
           boolean bl=userService.checkUserToken(userId,token);
           if (!bl) {
               retJson.setCode(Code.C2007);
               retJson.setInfo("账号信息失效，请重新登录后再尝试操作！");
               return JSONObject.fromObject(retJson).toString();
           }
           Page<ToInfo> list=todoInfoService.findListPageByCondition(model);
           retJson.setData(list);
           retJson.setStatus(1);
           retJson.setCode(Code.C1111);
           retJson.setInfo("操作成功！");
       }catch (Exception e){
           e.printStackTrace();
       }
        return JSONObject.fromObject(retJson).toString();
    }
}
