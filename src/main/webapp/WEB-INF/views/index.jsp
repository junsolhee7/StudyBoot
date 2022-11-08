<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>

<link href="/css/test.css" rel="stylesheet">
<c:import url="./temp/boot.jsp"></c:import>
<script defer src="/js/test.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-8">
					<h1>Index page</h1>
					<h1><spring:message code="hi"></spring:message></h1>
					<h1><spring:message code="test" text="code가 없을때 출력하는 메시지"></spring:message></h1>
					
					
					<img src="./images/winter.jpeg" id="id1">
					<a href="./qna/list">QNA</a>
					<div>
						<!-- <img alt="" src="/file/img5.jpg">
						<a href="/file/qna/img6.jpg">장어</a>
						<br>
						<img alt="" src="/file/qna/img6.jpg">
						<a href="/file/img5.jpg">삼겹살</a>
						<br>
						<img alt="" src="/file/qna/pptsample.pptx">
						<a href="/file/pptsample.pptx">삼겹살</a>
						
						<br>download
						<a href="/fileDown/qna?fileNum=2">삼겹살</a> -->
					</div>
			</div>
			<div>
				<a href="/member/add">회원가입</a>
				<div>
					<!-- 로그인 성공 -->
					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="Principal" var="member"/>
						<h3>${member.name}님 환영합니다.</h3>
						<h3><spring message code="welcome" argument="${member.name}"></spring></h3>
						<h3><spring message code="welcome2" argument="${member.id}_${member.name}" argumentSeparator=":"></spring></h3>
						
						<a href="./member/mypage">myPage</a>
						<a href="./member/logout">로그아웃</a>
						<form id="outForm" action="./member/logout" method="post">
							
						</form>

					</sec:authorize>					
					<!-- 로그인 전 -->
					<sec:authorize access="!isAuthenticated()">									
						<a href="./member/login">로그인</a>
						<a href="/oauth2/authorization/kakao">Kakao Login</a>
						<a href="./member/add">회원가입</a>
					</sec:authorize>
					<sec:authorize url="/admin" var="ad">
						<a href="/admin">GO Admin</a>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ADMIN','MANAGER')">
						<a href="/manager">GO Manager</a>
					</sec:authorize>
				</div>
				<button id="btn">CLICK</button>

				<button class="buttons">BTN1</button>
				<button class="buttons">BTN2</button>
				<button class="buttons">BTN3</button>
				
				<%-- <a href="https://kauth.kakao.com/oauth/logout?client_id=${df4281d957d19ff66538cf1d877567a1}&logout_redirect_uri=${http://localhost:81/member/logoutResult}">Kakao Logout</a> --%>
				

				<div id="test">
				</div>
				<h1>${h}</h1>
				<h1>${h}</h1>
				<h1>${h}</h1>
				<h1>${h}</h1>
			</div>
		</div>
	</div>
</body>
</html>