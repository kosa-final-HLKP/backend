package com.HLKPfinal.service;

import com.HLKPfinal.entity.Authority;
import com.HLKPfinal.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private AuthorityRepository authorityRepository;

    @Autowired
    public DataLoader(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public void run(ApplicationArguments args) {
        if (!authorityRepository.findByAuthorityStatus("ROLE_USER").isPresent()) {
            Authority userAuthority = Authority.builder()
                    .authorityStatus("ROLE_USER")
                    .build();
            authorityRepository.save(userAuthority);
        }

        if (!authorityRepository.findByAuthorityStatus("ROLE_ADMIN").isPresent()) {
            Authority adminAuthority = Authority.builder()
                    .authorityStatus("ROLE_ADMIN")
                    .build();
            authorityRepository.save(adminAuthority);
        }
    }


}
