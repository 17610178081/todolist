package org.example.todo.domain.service.impl;

import org.example.todo.domain.entity.User;
import org.example.todo.domain.repository.UserRepository;
import org.example.todo.domain.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * @program: todolist
 * @ClassName UserServiceImpl
 * @description:
 * @author: 好栖君(lyj)
 * @create: 2021-08-12 14:34
 * @Version 1.0
 **/
@Component("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findById(Long id){
        return  userRepository.findById(id).get();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User update(User user){
        return userRepository.save(user);
    }

    /** @Author 好栖君(lyj)
     * @Description 根据用户名查询用户
     * @Date 14:38 2021/8/12
     * @Param 
     * @return 
     **/

    public User findByUserName(String userName){
        return userRepository.findByUsername(userName);
    }

    /** @Author 好栖君(lyj)
     * @Description 核对用户token
     * @Date 14:58 2021/8/12
     * @Param 
     * @return 
     **/
    public boolean checkUserToken(Long id,String token){
        boolean bl=false;
        try {
            User u=userRepository.findById(id).get();
            if(u!=null){
                if(token.equals(u.getTokenValue())){
                    bl=true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bl;
    }

}
