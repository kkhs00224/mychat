package com.example.mychat_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity // 웹 보안 구성 중 몇 가지 세부사항 설정을 위해 두 개의 빈을 노출
public class WebSecurityConfig{

    @Bean // 보호해야 하는 URL 경로와 보호하지 않아야 하는 URL 경로를 정의
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers("/","/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form)->form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout)->logout.permitAll());

        return http.build();
    }

    @Bean
    //public UserDetailsService userDetailsService(){
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user =
                // User.withDefaultPasswordEncoder() // deprecated 메소드이지만, 테스트 실행을 위한 것이기 때문에 괜찮다. 실제 운영에 사용되지 않음.
                User.builder() // 하지만 bcrypt를 사용해 더 강력한 암호 인코딩 매커니즘을 사용하기 위해서 변경 가능하다.
                        .username("user")
                        //.password("password")
                        .password(passwordEncoder().encode("password"))
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}