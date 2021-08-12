package org.example.todo;

import org.example.todo.domain.entity.User;
import org.example.todo.domain.repository.UserRepository;
import org.example.todo.util.MD5;
import org.example.todo.util.UserToken;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi//开启Swagger3
public class ToDoListApplication {

    @Bean
    InitializingBean saveData(UserRepository ur){
        return ()->{
            ur.save(new User((long)1,"zhangsan", MD5.getMD5("zhangsan"), UserToken.makeToken("zhangsan"),1));
            ur.save(new User((long)2,"lisi", MD5.getMD5("lisi"), UserToken.makeToken("lisi"),1));
            ur.save(new User((long)3,"wangwu", MD5.getMD5("wangwu"), UserToken.makeToken("wangwu"),1));
            ur.save(new User((long)4,"zhaoliu", MD5.getMD5("zhaoliu"), UserToken.makeToken("zhaoliu"),1));

        };
    }
    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }

}
