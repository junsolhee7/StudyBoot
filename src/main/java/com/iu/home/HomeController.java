package com.iu.home;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.iu.home.board.qna.PostVO;
import com.iu.home.board.qna.QnaMapper;
import com.iu.home.board.qna.QnaVO;
import com.iu.home.member.MemberVO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
	
//	@Value("${my.message.hi}")
	private String message;
	@Value("${my.default}")
	private String app;
	
//	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "Admin Role";
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "Manager Role";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String member() {
		return "Member Role";
	}
////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/web")
	public String webClientTest() {		
		WebClient webClient = WebClient.builder()
												.baseUrl("htpps://jsonplaceholder.typicode.com/")
												.build();
		
		Flux<PostVO> res = webClient.get()
						.uri("posts/1")
						.retrieve()
						.bodyToFlux(PostVO.class);
										
		PostVO postVO = res.blockFirst();
		
		res.subscribe((s)->{
			PostVO pvo=s;
			log.info("s.getId()");
		});
		
		log.info("Result => {}",postVO);

		
		
		return"";
	}
	
	@GetMapping("/address")
	@ResponseBody
	public String address() throws Exception{
		//kakao ?????? ??????
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","KakaoAK df4281d957d19ff66538cf1d877567a1");
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("query", "?????? ?????????100");
		
		HttpEntity<MultiValueMap<String,String>> req = new HttpEntity<MultiValueMap<String,String>>(params,headers);
		ResponseEntity<String> res = restTemplate.getForEntity("https://dapi.kakao.com/v2/local/search/address.json",String.class,req);
		
		return res.getBody();
	}
	
	@GetMapping("/")
	public String home(HttpSession session) throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();// ????????????
		
		//1. header??????
		HttpHeaders headers = new HttpHeaders();
//		header : key:value
//		headers.add("key", "value");
//		headers.set?????????("???");
		
		//2. parameter??????
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>(); 
		params.add("key", "value");//2. parameter
		
		//3. ??????????????????
		HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);
		
		//4. ?????? ?????? ?????? ??????
		List<PostVO> posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts",List.class,request);
//		List<PostVO> postVO = response.getBody();
		log.info("Posts=>{}",posts);
		log.info("==========================");
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			log.info("Key => {}", key);
		}
		SecurityContextImpl context = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTENXT");
		if(context!=null) {
			log.info("Context=>{}",context);
		}
//		log.info("Context => {}",(MemberVO)(context.getAuthentication().getPrincipal()));  //????????????
		
		
		log.info("message {} ", message);
		log.info("default {} ", app);
		log.info("==========================");

		return "index";
	}

}