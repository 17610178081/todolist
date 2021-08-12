package org.example.todo.domain.repository;

import org.example.todo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {
    @Query("select u from User u where u.userName =:userName")
    User findByUsername(@Param("userName") String userName);
}
