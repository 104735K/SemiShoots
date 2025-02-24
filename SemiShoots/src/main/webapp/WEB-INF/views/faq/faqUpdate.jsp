<%--
	0. faq를 수정하기 위한 폼입니다.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.js"></script>
	<script src = "${pageContext.request.contextPath}/js/updateform.js"></script>
	<link rel = "stylesheet" href = "${pageContext.request.contextPath}/css/faqUpdate.css" type = "text/css">
	<title>FAQ 수정</title>
	<jsp:include page="../user/top.jsp"></jsp:include>
</head>
<body>
	<form action="updateProcess" method="post" enctype="multipart/form-data" name="modifyform">
		<input type="hidden" name="existing_file" value="${fb.faq_file}"> <!-- 기존 첨부파일명 전달 -->
  		<input type="hidden" name="remove_file" value="false"> <!-- 파일 삭제 여부 플래그 추가 -->
		<div class = "container">
			<input type="hidden" name="faq_id" value="${fb.faq_id}">
			<div class="form-group">
				<label>작성자</label>
				<input type="text" class="writer" value="${fb.name}" readOnly>
			</div>
			
			<div class="form-group">
				<label for="title">
				<span class="titlep">제목</span>
				</label>
				<input name="title" id="title" rows="1" maxlength="100"
						class="titleI" value="${fb.title}">
			</div>
		
			<%--faq 내용 --%>
			<div class="form-group">
				<textarea name="content" id="content" rows="10"
						class="content">${fb.content}</textarea>
			</div>
		
			<div class="form-group">
				<label>파일 첨부
					<img src="${pageContext.request.contextPath}/img/attach.png" alt="파일첨부" width="20px">
					<input type="file" id="upfile" name="faq_file">
				</label>
				<br>
				<span id="filevalue">${fb.faq_file}</span>
				<img src="${pageContext.request.contextPath}/img/remove.png" 
					alt="파일삭제" width="10px" class="remove">
			</div>
			
			<div class="btnD">
				<input type = "submit" class = "uploadbBtn" value = "수정" class = "btn btn-danger">
				<input type = "button" class = "backBtn" onClick="history.go(-1)" value = "목록">
			</div>
		</div>
	</form>
</body>
</html>
