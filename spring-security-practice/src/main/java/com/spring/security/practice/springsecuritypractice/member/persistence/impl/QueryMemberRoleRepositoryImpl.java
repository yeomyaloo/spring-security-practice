package com.spring.security.practice.springsecuritypractice.member.persistence.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.security.practice.springsecuritypractice.member.domain.entity.QMemberRole;
import com.spring.security.practice.springsecuritypractice.member.persistence.inter.QueryMemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class QueryMemberRoleRepositoryImpl implements QueryMemberRoleRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<String> findMemberRoleByMemberLoginId(String loginId) {
        QMemberRole memberRole = QMemberRole.memberRole;
        return jpaQueryFactory.select(memberRole.role.roleName)
                .from(memberRole).where(memberRole.member.loginId.eq(loginId)).fetch();
    }
}
