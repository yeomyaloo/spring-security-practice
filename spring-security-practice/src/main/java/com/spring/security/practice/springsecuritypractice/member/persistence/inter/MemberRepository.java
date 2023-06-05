package com.spring.security.practice.springsecuritypractice.member.persistence.inter;

import com.spring.security.practice.springsecuritypractice.member.domain.entity.Member;
import com.spring.security.practice.springsecuritypractice.member.persistence.common.CommonMemberRepository;
import org.springframework.data.repository.Repository;


public interface MemberRepository extends CommonMemberRepository, Repository<Member, Long> {
}
