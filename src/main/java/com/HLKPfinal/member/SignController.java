package com.HLKPfinal.member;

import com.HLKPfinal.member.dto.SignRequest;
import com.HLKPfinal.member.dto.SignResponse;
import com.HLKPfinal.repository.MemberRepository;
//import com.HLKPfinal.security.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final MemberRepository memberRepository;
    private final SignService memberService;

    @PostMapping(value = "/login")
    public ResponseEntity<SignResponse> login(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(memberService.login(request), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Boolean> signup(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(memberService.register(request), HttpStatus.OK);
    }

    @GetMapping("/user/get")
    public ResponseEntity<SignResponse> getUser(@RequestParam String email) throws Exception {
        return new ResponseEntity<>( memberService.getMember(email), HttpStatus.OK);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<SignResponse> getUserForAdmin(@RequestParam String email) throws Exception {
        return new ResponseEntity<>( memberService.getMember(email), HttpStatus.OK);
    }



//    @GetMapping("/refresh")
//    public ResponseEntity<TokenDto> refresh(@RequestBody TokenDto token) throws Exception {
//        return new ResponseEntity<>( memberService.refreshAccessToken(token), HttpStatus.OK);
//    }


}