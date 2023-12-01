package com.HLKPfinal.config;

import com.HLKPfinal.service.CustomUserDetailsService;
import com.HLKPfinal.service.MemberService;
import lombok.RequiredArgsConstructor;
import com.HLKPfinal.jwt.JwtAccessDeniedHandler;
import com.HLKPfinal.jwt.JwtAuthenticationEntryPoint;
import com.HLKPfinal.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final StringRedisTemplate redisTemplate;
    private final MemberService memberService;
    private final CustomUserDetailsService customUserDetailsService;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ID, Password 문자열을 Base64로 인코딩하여 전달하는 구조
                .httpBasic().disable()
                // 쿠키 기반이 아닌 JWT 기반이므로 사용하지 않음
                .csrf().disable()

                .cors()
                // CORS 설정
//                .cors(c -> {
//                            CorsConfigurationSource source = request -> {
//                                // Cors 허용 패턴
//                                CorsConfiguration config = new CorsConfiguration();
//                                config.setAllowedOrigins(
//                                        List.of( "*","https://fe-web-eight.vercel.app","https://fe-web-eight.vercel.app/**")
//                                );
//                                config.setAllowedMethods(
//                                        List.of("*", "POST", "GET")
//                                );
//                                return config;
//                            };
//                            c.configurationSource(source);
//                        }
//                )
                .and()
                // jwt token 인증, 세션 Stateless
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

//                .and()
//                // Spring Security 세션 정책 : 세션을 생성 및 사용하지 않음
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 조건별로 요청 허용/제한 설정
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/auth/**").permitAll()
//                .antMatchers("/register", "/login").permitAll()
                // /admin으로 시작하는 요청은 ADMIN 권한이 있는 유저에게만 허용
                .antMatchers("/parent/**").hasRole("ADMIN")
                // /user 로 시작하는 요청은 USER 권한이 있는 유저에게만 허용
                .antMatchers("/member/**","/video/**", "/attendance/**").hasRole("USER")
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근
//                .and()
//                // JWT 인증 필터 적용
//                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider, redisTemplate), UsernamePasswordAuthenticationFilter.class)

//                // 로그아웃 설정
//                .logout()
//                .logoutUrl("/logout") // 로그아웃 엔드포인트
//
//                .deleteCookies("JWT-TOKEN") // 필요에 따라 쿠키 삭제
//                .logoutSuccessHandler((request, response, authentication) -> {
//                    // 로그아웃 성공 시 처리 로직
//                    response.setStatus(HttpServletResponse.SC_OK);
//                    response.setCharacterEncoding("utf-8");
//                    response.setContentType("application/json");
//                    response.getWriter().write("{\"message\": \"로그아웃이 성공적으로 처리되었습니다.\"}");
//                })
//                .and()
                // 에러 핸들링
//                .exceptionHandling()
//                .accessDeniedHandler(new AccessDeniedHandler() {
//                    @Override
//                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//                        // 권한 문제가 발생했을 때 이 부분을 호출한다.
//                        response.setStatus(403);
//                        response.setCharacterEncoding("utf-8");
//                        response.setContentType("text/html; charset=UTF-8");
//                        response.getWriter().write("권한이 없는 사용자입니다.");
//                    }
//                })
//                .authenticationEntryPoint(new AuthenticationEntryPoint() {
//                    @Override
//                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//                        // 인증문제가 발생했을 때 이 부분을 호출한다.
//                        response.setStatus(401);
//                        response.setCharacterEncoding("utf-8");
//                        response.setContentType("text/html; charset=UTF-8");
//                        response.getWriter().write("인증되지 않은 사용자입니다.");
//                    }
//                });

                // exception handling
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider, redisTemplate));

        return http.build();
    }

    @Bean
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder (new BCryptPasswordEncoder());
    }




//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                // rest api, 로그인 폼 화면 disable
//                .httpBasic().disable()
//
//                // rest api, CSRF 보안 disable
//                .csrf().disable()
//                .cors()
//
//                .and()
//                // jwt token 인증, 세션 Stateless
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                // exception handling
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler)
//
//                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
//                .and()
//                .apply(new JwtSecurityConfig(tokenProvider, redisTemplate));
//    }
}