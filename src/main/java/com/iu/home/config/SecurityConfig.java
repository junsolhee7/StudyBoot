package com.iu.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	//public 을 선언하면 default로 바꾸라는 메시지 출력
	WebSecurityCustomizer webSecurityConfig() {
		//Security에서 무시해야하는 URL 패턴 등록
		return web -> web
				.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/favicon/**")
				.antMatchers("/resources/**");
	}
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		//interceptor 와 비슷한역할
		httpSecurity
					.cors()
					.and()
					.csrf()
					.disable()
				.authorizeRequests()
					.antMatchers("/","/qna/list").permitAll()
					.antMatchers("/admin").hasRole("ADMIN")
					.antMatchers("/manager").hasRole("MANAGER")
//					.antMatchers("/qna/detail").hasRole("USER")
//					.antMatchers("/qna/update").hasRole("USER")
//					.antMatchers("/qna/delete").hasRole("USER")
					.antMatchers("/qna/list").permitAll()
					.antMatchers("/qna/**").hasRole("MEMBER")
					.anyRequest().permitAll()
//					.anyRequest().authenticated()
					.and()
					
				.formLogin()
					.loginPage("/member/login") 		// 내장된 로그인폼을 사용하지 않고 개발자가 만든 폼 요청
//					.loginProcessingUrl("login")		// 로그인을 진행요청할 form 태그의 action의 주소지정
					.passwordParameter("pw")			// 패스워드 파라미터는 password이지만, 개발자가 다른 파라미터 이름을 사용할때
					.usernameParameter("id")			// id 파라미터는 username이지만, 개발자가 다른 파라미터 이름을 사용할때
					.defaultSuccessUrl("/")				// 인증에 성공할 경우 요청할 URL
					.failureUrl("/member/login")		// 인증에 실패했을 경우 요청할 URL
					.permitAll()
					.and()
				.logout()
					.logoutUrl("/out")
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.permitAll();
		return httpSecurity.build();
	}
	//평문 (Clear Text)을 암호화 시켜주는 객체생성
	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
}
