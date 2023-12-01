package com.HLKPfinal.entity;

import lombok.*;
import com.HLKPfinal.dto.MemberUpdateDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    @Column(name="auth")
    private String auth;


//    @ManyToMany
//    @JoinTable(
//            name = "member_authority",
//            joinColumns = {@JoinColumn(name="member_id",referencedColumnName = "member_id")},
//            inverseJoinColumns = {@JoinColumn(name = "authority_status",referencedColumnName = "authority_status")})
//    private Set<Authority> authorities = new HashSet<>();


    @Builder
    public Member(String email, String name, String password, String auth) {
        this.email = email;
        this.name = name;
        this.password = password;
//        this.authorities = authorities;   Set<Authority> authorities
        this.auth = auth;
    }

//    // 회원 정보 수정
//    public void updateMember(MemberUpdateDto dto, PasswordEncoder passwordEncoder) {
//        if(dto.getPassword() != null) this.password = passwordEncoder.encode(dto.getPassword());
//        if(dto.getName() != null) this.name = dto.getName();
//    }


    // 사용자의 권한을 콜렉션 형태로 반환
    // 단, 클래스 자료형은 GrantedAuthority를 구현해야함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    // 사용자의 email를 반환 (unique한 값)
    @Override
    public String getUsername() {
        return email;
    }

    // 사용자의 password를 반환
    @Override
    public String getPassword() {
        return password;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않았음
    }

    // 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        return true; // true -> 사용 가능
    }


}