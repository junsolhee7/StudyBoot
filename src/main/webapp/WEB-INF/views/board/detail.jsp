<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail.jsp</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-8">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>NUM</th>
							<th>TILTE</th>
							<th>WRITER</th>
							<th>HIT</th>
							<th>DATE</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${requestScope.vo.num}</td>
							<td>${requestScope.vo.title}</td>
							<td><a href="./detail?num=${vo.num}">${requestScope.vo.writer}</a></td>
							<td>${requestScope.vo.hit}</td>
							<td>${requestScope.vo.regDate}</td>
						</tr>	
					</tbody>
				</table>
				
			</div>
		</div>
	</div>
</body>
</html>