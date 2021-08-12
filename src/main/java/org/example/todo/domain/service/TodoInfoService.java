package org.example.todo.domain.service;

import org.example.todo.domain.entity.ToInfo;
import org.example.todo.vo.TodoInfoPageVo;
import org.springframework.data.domain.Page;

/**
 * @program: todolist
 * @ClassName TodoInfoService
 * @description:
 * @author: 好栖君(lyj)
 * @create: 2021-08-12 15:28
 * @Version 1.0
 **/
public interface TodoInfoService {

    Page<ToInfo> findListPageByCondition(TodoInfoPageVo todoInfoPageVo);
    ToInfo findById(Long id);

    ToInfo save(ToInfo toInfo);

    ToInfo update(ToInfo toInfo);
}