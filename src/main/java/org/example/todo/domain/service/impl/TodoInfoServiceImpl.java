package org.example.todo.domain.service.impl;

import org.example.todo.domain.entity.ToInfo;
import org.example.todo.domain.repository.TodoInfoRepository;
import org.example.todo.domain.service.TodoInfoService;
import org.example.todo.vo.TodoInfoPageVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: todolist
 * @ClassName TodoInfoServiceImpl
 * @description:
 * @author: 好栖君(lyj)
 * @create: 2021-08-12 16:57
 * @Version 1.0
 **/
@Component("todoInfoService")
@Transactional
public class TodoInfoServiceImpl implements TodoInfoService {
    private TodoInfoRepository todoInfoRepository;

    TodoInfoServiceImpl(TodoInfoRepository todoInfoRepository) {
        this.todoInfoRepository = todoInfoRepository;
    }

    @Override
//    public Page<ToInfo> findListPageByCondition(TodoInfoPageVo todoInfoPageVo) {
//        Pageable pageable = PageRequest.of(todoInfoPageVo.getStart(), todoInfoPageVo.getLength());
//        Specification querySpecification = (Specification<ToInfo>) (root, query, cb) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            query.orderBy(cb.desc(root.get("id").as(Long.class)));
//            if (todoInfoPageVo.getStatus() != null) {
//                predicates.add(cb.equal(root.get("status"), todoInfoPageVo.getStatus()));
//            }
//            if (todoInfoPageVo.getUserId() != null&&todoInfoPageVo.getUserId()>0) {
//                predicates.add(cb.equal(root.get("userId"), todoInfoPageVo.getUserId()));
//            }
//            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//        };
//        return todoInfoRepository.findAll(querySpecification,pageable);
//    }
    public Page<ToInfo> findListPageByCondition( TodoInfoPageVo todoInfoPageVo) {
        Pageable pageable = PageRequest.of(todoInfoPageVo.getStart(), todoInfoPageVo.getLength());
        Page<ToInfo> page = todoInfoRepository.findAll(new Specification<ToInfo>() {
            @Override
            public Predicate toPredicate(Root<ToInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (todoInfoPageVo.getStatus()!=null){

                    list.add(criteriaBuilder.equal(root.get("status"),todoInfoPageVo.getStatus()));
                }
                if (todoInfoPageVo.getUserId() != null&&todoInfoPageVo.getUserId()>0){

                    list.add(criteriaBuilder.equal(root.get("userId"),todoInfoPageVo.getUserId()));
                }
                //重点
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return page;
    }

    @Override
    public ToInfo findById(Long id) {
       return todoInfoRepository.findById(id).get();
    }

    @Override
    public ToInfo save(ToInfo toInfo) {
        todoInfoRepository.save(toInfo);
        return toInfo;
    }

    @Override
    public ToInfo update(ToInfo toInfo) {
        todoInfoRepository.save(toInfo);
        return toInfo;
    }


}
