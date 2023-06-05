package com.spring.security.practice.springsecuritypractice.member.persistence.common;

import com.spring.security.practice.springsecuritypractice.member.domain.entity.Member;

public interface CommonMemberRepository {

    Member save(Member member);

}
