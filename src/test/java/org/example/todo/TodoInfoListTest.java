package org.example.todo;

/**
 * @program: todolist
 * @ClassName TodoInfoListTest
 * @description:
 * @author: 好栖君(lyj)
 * @create: 2021-08-12 22:08
 * @Version 1.0
 **/

import net.sf.json.JSONObject;
import org.example.todo.domain.entity.User;
import org.example.todo.domain.service.TodoInfoService;
import org.example.todo.domain.service.UserService;
import org.example.todo.util.MD5;
import org.example.todo.util.UserToken;
import org.example.todo.vo.LoginVo;
import org.example.todo.vo.ToInfoVo;
import org.example.todo.vo.TodoInfoPageVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//底层用junit SpringJunit4ClassRunner
@RunWith(SpringRunner.class)
//启动整个Springboot工程
@SpringBootTest(classes= {ToDoListTestApplication.class})
@AutoConfigureMockMvc
public class TodoInfoListTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @MockBean
    private TodoInfoService todoInfoService;

	@Test
	public  void initUser(){
		userService.save(new User((long)1,"zhangsan", MD5.getMD5("zhangsan"), UserToken.makeToken("zhangsan"),1));
		userService.save(new User((long)2,"lisi", MD5.getMD5("lisi"), UserToken.makeToken("lisi"),1));
		userService.save(new User((long)3,"wangwu", MD5.getMD5("wangwu"), UserToken.makeToken("wangwu"),1));
		userService.save(new User((long)4,"zhaoliu", MD5.getMD5("zhaoliu"), UserToken.makeToken("zhaoliu"),1));
	}

    /** @Author 好栖君(lyj)
     * @Description //模拟用户登录
     * @Date 22:45 2021/8/12
     * @Param 
     * @return 
     **/
    @Test
    public void UserAPIForLogin() throws Exception {
        LoginVo model=new LoginVo();
        model.setUsername("zhangsan");
        model.setPassword("zhangsan");
        String requestJson = JSONObject.fromObject(model).toString();
        // mockbean 模拟完成
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

    }
    /** @Author 好栖君(lyj)
     * @Description //模拟用户退出登录
     * @Date 22:46 2021/8/12
     * @Param 
     * @return 
     **/
    @Test
    public void UserAPITestForLoginOut() throws Exception {
       Long id=1L;
       String token="f893c349617f4e67a2ba0c205d4292d4";
        // mockbean 模拟完成
        mockMvc.perform(MockMvcRequestBuilders.get("/user/login_out/"+id+"/"+token))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }
    /** @Author 好栖君(lyj)
     * @Description //模拟保存代办事项
     * @Date 22:46 2021/8/12
     * @Param 
     * @return 
     **/
    @Test
    public void TodoInfoAPITestForSave() throws Exception {
        Long id=1L;
        String token="f893c349617f4e67a2ba0c205d4292d4";
        ToInfoVo model=new ToInfoVo();
        model.setTodoName("代办事项名称");
        model.setTodoRemark("代办事项备注");
        model.setStatus(0);
        String requestJson = JSONObject.fromObject(model).toString();
        // mockbean 模拟完成
        mockMvc.perform(MockMvcRequestBuilders.post("/todo_info/save/"+id+"/"+token)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

    }
    /** @Author 好栖君(lyj)
     * @Description //模拟更新代办事项
     * @Date 22:46 2021/8/12
     * @Param 
     * @return 
     **/
    @Test
    public void TodoInfoAPITestForUpdate() throws Exception {
        Long id=1L;
        String token="f893c349617f4e67a2ba0c205d4292d4";
        ToInfoVo model=new ToInfoVo();
        model.setId(1L);
        model.setTodoName("代办事项名称");
        model.setTodoRemark("代办事项备注");
        model.setStatus(1);
        String requestJson = JSONObject.fromObject(model).toString();
        // mockbean 模拟完成
        mockMvc.perform(MockMvcRequestBuilders.post("/todo_info/update/"+id+"/"+token)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

    }
    /** @Author 好栖君(lyj)
     * @Description //模拟条件查询待办事件列表，分页
     * @Date 22:52 2021/8/12
     * @Param 
     * @return 
     **/
    @Test
    public void TodoInfoAPITestForFindListPageByCondition() throws Exception {
        Long id=1L;
        String token="f893c349617f4e67a2ba0c205d4292d4";
        TodoInfoPageVo model=new TodoInfoPageVo();
        model.setStart(0);
        model.setLength(10);
        model.setStatus(1);
       // model.setUserId(1L);
        String requestJson = JSONObject.fromObject(model).toString();
        // mockbean 模拟完成
        mockMvc.perform(MockMvcRequestBuilders.post("/todo_info/find_listpage_by_condition/"+id+"/"+token)
                .contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

    }
}
