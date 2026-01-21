package com.example.shop.config;

import com.example.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration              // Spring 설정 클래스
@EnableWebSecurity          // Spring Security 활성화
@RequiredArgsConstructor    // @Autowired 대체, final 필드 자동 생성자 생성
public class SecurityConfig {

    private final MemberService memberService;  // final 추가로 불변성 보장

    @Bean   // SecurityFilterChain 빈 등록
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http     // HttpSecurity 체이닝 시작

                // 🛡️ 1. CSRF 보호 설정 (Cross-Site Request Forgery)
                .csrf(csrf -> csrf
                        .csrfTokenRepository(new CookieCsrfTokenRepository())   // 세션 대신 httpOnly 쿠키 저장(AJAX 안전)
                )


                // 🍪 2. CSRF 쿠키 자동 생성 필터 추가
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)


                // 🔐 3. 권한/인증 설정
                .authorizeHttpRequests(auth -> auth
                                        .requestMatchers("/css/**", "/jsl/**", "/img/**").permitAll()
                                        // 정적 리소스 공개(Thymeleaf, Bootstrap 등)
                                        .requestMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
                                        // 쇼핑몰 공개 URL(회원가입/로그인/상품목록)
                                        .requestMatchers("/admin/**").hasRole("ADMIN")
                                        // 관리자 페이지 ADMIN 역할만
                                        .anyRequest()
                                        // 그 외 모든 요청
                                        .authenticated()
                                        // 로그인 필수


                // 🔑 4. 로그인 폼 설정
                ).formLogin(formLoginCustomizer -> formLoginCustomizer
                                .loginPage("/members/login")
                                // 커스텀 로그인 페이지
                                .defaultSuccessUrl("/", true)
                                // 로그인 성공 시 메인 페이지 (true : 항상 리다이렉트)
                                .usernameParameter("email")
                                // 이메일로 로그인 (기본 : username)
                                .failureHandler(new FormLoginAuthenticationFailureHandler())
                        // 로그인 실패 커스텀 핸들러 (에러 메시지 표시)


                // 🚪 5. 로그아웃 설정
                ).logout(logout -> logout
                                .logoutUrl("/members/logout")
                                // 로그아웃 URL
                                .logoutSuccessUrl("/")
                        // 로그아웃 성공 시 메인 페이지


                // ⚠️ 6. 인증/인가 예외 처리
                ).exceptionHandling(e -> e
                                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                        // 미인증 시 커스텀 처리 (AJAX 401 등)
                )
                .build()
                // 필터 체인 빌드 및 반환
                ;
    }

    @Bean
    // 비밀번호 암호화 빈
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
