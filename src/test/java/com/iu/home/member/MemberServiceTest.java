package com.iu.home.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
class MemberServiceTest {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void test() throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId("admin1");
		
		memberVO.setPw(passwordEncoder.encode("admin1"));
		memberVO.setName("admin1");
		memberVO.setEmail("admin01@gmail.com");
		
		int result = memberMapper.setAdd(memberVO);
		assertEquals(1,result);
		/////////////manager추가//////////
		memberVO.setId("manager1");
		
		memberVO.setPw(passwordEncoder.encode("manager1"));
		memberVO.setName("manager1");
		memberVO.setEmail("manager01@gmail.com");
		
		result = memberMapper.setAdd(memberVO);
		assertEquals(1,result);
	}

}
