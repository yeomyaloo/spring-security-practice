package com.spring.security.practice.springsecuritypractice.member.persistence.inter;

import com.spring.security.practice.springsecuritypractice.member.domain.dto.response.MemberResponse;
import com.spring.security.practice.springsecuritypractice.member.domain.entity.Member;

public interface QueryMemberRepository {

    Member findMemberByLoginId(String loginId);
}
