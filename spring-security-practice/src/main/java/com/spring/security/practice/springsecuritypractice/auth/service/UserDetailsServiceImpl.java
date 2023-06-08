package com.spring.security.practice.springsecuritypractice.auth.service;

import com.spring.security.practice.springsecuritypractice.member.domain.entity.Member;
import com.spring.security.practice.springsecuritypractice.member.persistence.inter.QueryMemberRepository;
import com.spring.security.practice.springsecuritypractice.member.persistence.inter.QueryMemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final QueryMemberRepository queryMemberRepository;
    private final QueryMemberRoleRepository queryMemberRoleRepository;


    /**
     * @param username http request에서 넘어온 회원 정보로 이 정보를 이용해서 db에 실제 데이터가 있는지를 찾아온다.
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = queryMemberRepository.findMemberByLoginId(username);
        List<String> roles = queryMemberRoleRepository.findMemberRoleByMemberLoginId(username);

        if (Objects.isNull(member)){
            throw new RuntimeException("not found member");
        }

        User user = new User(member.getLoginId(),
                member.getPassword(),
                roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );

        return user;
    }
}
