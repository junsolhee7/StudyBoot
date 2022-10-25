<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>

<c:import url="./temp/boot.jsp"></c:import>
<link href="/css/test.css" rel="stylesheet">

</head>
<body>
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-8">
					<h1>Index page</h1>
					<img src="./images/winter.jpeg" id="id1">
					<a href="./qna/list">QNA</a>
					<div>
						<img alt="" src="/file/img5.jpg">
						<a href="/file/qna/img6.jpg">장어</a>
						<br>
						<img alt="" src="/file/qna/img6.jpg">
						<a href="/file/img5.jpg">삼겹살</a>
						<br>
						<img alt="" src="/file/qna/pptsample.pptx">
						<a href="/file/pptsample.pptx">삼겹살</a>
						
						<br>download
						<a href="/fileDown/qna?fileNum=2">삼겹살</a>
					</div>
			</div>
			<div>
				<a href="/member/add">회원가입</a>
				<div>
					<c:choose>
						<c:when test="${not empty member}">
							<a href="./member/logout">로그아웃</a>				
						</c:when>
						<c:otherwise>
							<a href="./member/login">로그인</a>
							<a href="./member/add">회원가입</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>