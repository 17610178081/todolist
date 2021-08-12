package org.example.todo.domain.repository;

import org.example.todo.domain.entity.ToInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoInfoRepository extends JpaRepository<ToInfo,Long>, JpaSpecificationExecutor<ToInfo> {
    @Query("select t from ToInfo t where t.userId =:userId")
    List<ToInfo> findListByUserId(@Param("userId") Long userId);
}
