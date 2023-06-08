package com.spring.security.practice.springsecuritypractice.member.persistence.inter;

import java.util.List;

public interface QueryMemberRoleRepository {


    List<String> findMemberRoleByMemberLoginId(String loginId);
}
