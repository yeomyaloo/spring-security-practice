package com.spring.security.practice.springsecuritypractice.member.persistence.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.security.practice.springsecuritypractice.member.domain.dto.response.MemberResponse;
import com.spring.security.practice.springsecuritypractice.member.persistence.inter.QueryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class QueryMemberRepositoryImpl implements QueryMemberRepository {


    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public MemberResponse findMemberByLoginId(String loginId) {
        return null;
    }
}
