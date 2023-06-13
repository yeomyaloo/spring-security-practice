package com.spring.security.practice.springsecuritypractice.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * query dsl에서 사용될 jpaQueryFactory 빈 등록을 위한 설정 클래스입니다.
 * */
@Configuration
public class QueryDslConfiguration {
    @PersistenceContext
    private EntityManager entityManager;
    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }

}
