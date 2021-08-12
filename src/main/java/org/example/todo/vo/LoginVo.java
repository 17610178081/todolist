package org.example.todo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * @program: todolist
 * @ClassName LoginVo
 * @description:
 * @author: 好栖君(lyj)
 * @create: 2021-08-12 10:31
 * @Version 1.0
 **/
@Getter
@Setter
public class LoginVo {
    private Long id;

    @NotEmpty(message = "登陆账号不得为空")
    private String username;

    @NotEmpty(message = "用户名不得为空")
    @JsonProperty(access = WRITE_ONLY)
    private String password;

}
