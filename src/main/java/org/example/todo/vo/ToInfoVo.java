package org.example.todo.vo;

import lombok.Getter;
import lombok.Setter;

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
public class ToInfoVo {
    private Long id;
    private String todoName;
    private String todoRemark;
    private Integer status;

}
