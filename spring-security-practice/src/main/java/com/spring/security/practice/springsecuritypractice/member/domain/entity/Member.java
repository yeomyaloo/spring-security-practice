package com.spring.security.practice.springsecuritypractice.member.domain.entity;


import com.spring.security.practice.springsecuritypractice.member.common.converter.MemberRoleConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.List;

@Table(name = "members")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", unique = true)
    private String loginId;
    private String password;
    private String name;
    @Column(unique = true, name = "mobile_phone_number")
    private String mobilePhoneNumber;
    private String birthday;
    @Column(unique = true)
    private String nickname;


}
