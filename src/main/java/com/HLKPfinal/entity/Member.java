package com.HLKPfinal.entity;

import com.HLKPfinal.dto.MemberUpdateDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name="email",unique = true)
    private String email;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

//    @Column(name = "reference_email")
//    private String referenceEmail; // 추가된 필드

    @Column(name = "reference_email")
    private String referenceEmail; // 수정된 필드


//    @OneToOne(mappedBy = "referenceMember")
//    private Member referencedBy; // 현재 Member 객체를 참조하는 다른 Member 객체


    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @Builder
    public Member(String email, String name, String password, Authority authority, String referenceEmail) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.authority = authority;
        this.referenceEmail = referenceEmail;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

//    public void setReferenceMember(Member referenceMember) {
//        this.referenceMember = referenceMember;
//    }

//    public Member getReferencedBy() {
//        return this.referencedBy;
//    }



    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Baby> babies = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Ai> aiList = new ArrayList<>();


    // 회원 정보 수정
    public void updateMember(MemberUpdateDto dto, PasswordEncoder passwordEncoder) {
        if(dto.getPassword() != null) this.password = passwordEncoder.encode(dto.getPassword());
        if(dto.getName() != null) this.name = dto.getName();
    }


}
