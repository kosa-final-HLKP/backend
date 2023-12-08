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

    @Column
    private String name;

    @Column
    private String password;


//    @Enumerated(EnumType.STRING)
//    private AuthorityEnum role;



//    @ManyToMany
//    @JoinTable(
//            name = "member_authority",
//            joinColumns = {@JoinColumn(name="member_id",referencedColumnName = "member_id")},
//            inverseJoinColumns = {@JoinColumn(name = "authority_status",referencedColumnName = "authority_status")})
//    private Set<Authority> authorities = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_status", referencedColumnName = "authority_status")})
    private Set<Authority> authorities = new HashSet<>();


    @Builder
    public Member(String email, String name, String password, Set<Authority> authorities) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.authorities = authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    // 회원 정보 수정
    public void updateMember(MemberUpdateDto dto, PasswordEncoder passwordEncoder) {
        if(dto.getPassword() != null) this.password = passwordEncoder.encode(dto.getPassword());
        if(dto.getName() != null) this.name = dto.getName();
    }

}
