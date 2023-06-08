package com.spring.security.practice.springsecuritypractice.member.persistence.inter;

import com.spring.security.practice.springsecuritypractice.member.domain.dto.response.MemberResponse;

public interface QueryMemberRepository {

    MemberResponse findMemberByLoginId(String loginId);
}
