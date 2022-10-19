<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:import url="./temp/boot.jsp"></c:import>
<link href="/css/test.css" rel="stylesheet">

</head>
<body>
	<h1>Index page</h1>
	<img src="./images/winter.jpeg" id="id1">
	<a href="./qna/list">QNA</a>
	
	<script type="text/javascript">
		int result = '${result}';
		if(result != ""){
			if(result=='1'){
				alert('등록성공';)
			}else{
				alert('등록실패');
			}
		}
	</script>
</body>
</html>