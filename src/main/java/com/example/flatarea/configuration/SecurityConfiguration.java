package com.example.flatarea.configuration;

import com.example.flatarea.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();     //네이버스마트에디터 추가를 위해 작성.

        http.authorizeRequests()
                .antMatchers(
                        "/"
                        , "/member/register"
                        , "/product"
                        , "/product/{id}",
                        "/files/**"

                        //제품 상세정보 페이지는 로그인 없이 접속가능, 하지만 구매하기 버튼을 눌렀을 때
                                            // /product/purchase 로 이동하는데 왜 로그인을 요구 안 하는지 ?

                                            // 일단 /purchase 로 변경해놨음.  (2022-12-14)
                )
                .permitAll();

        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasAuthority("ROLE_ADMIN");

        http.formLogin()
                .loginPage("/member/login")
                .failureHandler(getFailureHandler())
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

        /**
         * ROLE_USER 가 admin 페이지로 접근할 때 발생하는 exception 페이지 처리.
         */
        http.exceptionHandling()
                .accessDeniedPage("/common/denied");    //    /error/denied를 실제 페이지 url 써도 문제 없었음 common은 ??


        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(memberService)
                .passwordEncoder(getPasswordEncoder());

        super.configure(auth);
    }


}
