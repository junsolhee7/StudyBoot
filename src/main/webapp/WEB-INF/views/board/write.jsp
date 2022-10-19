<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>
	<h1>Board Write Page</h1>
	<div class="container-fluid">
	<div class="row justify-content-center">
		<div class="col-6">
		<form action="add" method="post" enctype="multipart/form-data">
			<div class="mb-3">
				<label for="title" class="form-label">Title</label>
				<input type="text" name="title" class="form-control" id="title" placeholder="제목">
			</div>
			<div class="mb-3">
				<label for="writer" class="form-label">Writer</label>
				<input type="text" name="writer" class="form-control" id="writer" placeholder="작성자">
			</div>
			<div class="mb-3">
				<label for="contents" class="form-label">Contents</label>
				<textarea type="text" name="title" class="form-control" id="contents" placeholder="내용"></textarea>
			</div>
			<div class="mb-3">
				<label for="contents" class="form-label">Contents</label>
				<input type="file" name="files">
			</div>
			<div class="mb-3">
				<label for="contents" class="form-label">Contents</label>
				<input type="file" name="files">
			</div>
			
			<div>
				<button class="btn btn-danger">WRITE</button>
			</div>
		</form>
		</div>
	</div>
	</div>
	<script tyoe="text/javascript">
    $('#contents').summernote({
      tabsize: 2,
      height: 120,
    });
    
	
</body>
</html>