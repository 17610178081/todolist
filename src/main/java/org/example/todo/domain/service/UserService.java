package org.example.todo.domain.service;

import org.example.todo.domain.entity.User;

/**
 * @program: todolist
 * @ClassName UserService
 * @description:
 * @author: 好栖君(lyj)
 * @create: 2021-08-12 14:32
 * @Version 1.0
 **/
public interface UserService {

    User findById(Long id);
    User findByUserName(String userName);

    User save(User user);

    User update(User user);

    boolean checkUserToken(Long id,String token);
}