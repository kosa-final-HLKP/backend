package com.HLKPfinal.entity;

import com.HLKPfinal.dto.MemberUpdateDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;


    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "member_roles", joinColumns = @JoinColumn(name = "member_id"))
    private Set<Role> roles = new HashSet<>();


//    @ManyToMany
//    @JoinTable(
//            name = "member_authority",
//            joinColumns = {@JoinColumn(name="member_id",referencedColumnName = "member_id")},
//            inverseJoinColumns = {@JoinColumn(name = "authority_status",referencedColumnName = "authority_status")})
//    private Set<Authority> authorities = new HashSet<>();
//
//    @Builder
//    public Member(String email, String name, String password, Set<Authority> authorities) {
//        this.email = email;
//        this.name = name;
//        this.password = password;
//        this.authorities = authorities;
//    }


    @Builder
    public Member(String email, String name, String password, Role role, Set<Role> roles) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.roles = roles;
    }

    // 회원 정보 수정
    public void updateMember(MemberUpdateDto dto, PasswordEncoder passwordEncoder) {
        if(dto.getPassword() != null) this.password = passwordEncoder.encode(dto.getPassword());
        if(dto.getName() != null) this.name = dto.getName();
    }
}
