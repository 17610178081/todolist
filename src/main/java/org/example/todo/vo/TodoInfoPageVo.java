package org.example.todo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: todolist
 * @ClassName TodoInfoPageVo
 * @description:
 * @author: 好栖君(lyj)
 * @create: 2021-08-12 16:48
 * @Version 1.0
 **/
@Getter
@Setter
public class TodoInfoPageVo {
    @ApiModelProperty(value = "开始" )
    private int start;

    @ApiModelProperty(value = "长度" )
    private  int length;

    @ApiModelProperty(value = "用户id" )
    private Long userId;

    @ApiModelProperty(value = "状态 0未完成，1已完成" )
    private Integer status;

}
