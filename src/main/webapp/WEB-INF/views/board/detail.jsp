<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail.jsp</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
	<h1>Detail Page</h1>
	<h3>Title : ${vo.title}</h3>
	<c:forEach items="${vo.qnaFileVOs}" var="fileVO">
		<img alt="" src="/file/qna/${fileVO.fileName}">
		<a href="/fileDown/qna?fileNum=${fileVO.fileNum}">${fileVO.oriName}</a>
	</c:forEach>
	<div>
		<a href="/qna/update?num=}">${fileVO.oriName}</a>
	</div>
	<div>
		<a href="./update?num=${vo.num}">수정</a>
	</div>

</body>
</html>