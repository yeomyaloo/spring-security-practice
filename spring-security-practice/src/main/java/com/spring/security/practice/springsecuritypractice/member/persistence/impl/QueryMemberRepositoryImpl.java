package com.spring.security.practice.springsecuritypractice.member.persistence.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.security.practice.springsecuritypractice.member.domain.dto.response.MemberResponse;
import com.spring.security.practice.springsecuritypractice.member.domain.entity.Member;
import com.spring.security.practice.springsecuritypractice.member.domain.entity.QMember;
import com.spring.security.practice.springsecuritypractice.member.persistence.inter.QueryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;


@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryMemberRepositoryImpl implements QueryMemberRepository {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Member findMemberByLoginId(String loginId) {

        QMember member = QMember.member;

        return jpaQueryFactory.selectFrom(member).where(member.loginId.eq(loginId))
                .fetchOne();
    }
}
