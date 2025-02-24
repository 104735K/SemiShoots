<%@ page language="java" contentType="text/html; charset=UTF-8;"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath }/js/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/report.css">


<title>신고창</title>
</head>
<body>

<c:if test="${postdata.writer != idx and role == 'common'}">
<button class ="btn-light postReportButton" data-toggle="modal" data-target=".report-modal"
style ="border:none"> <img src = "${pageContext.request.contextPath}/img/report.png" style = "width : 20px"> </button>
</c:if>
	<!-- 모달창 시작-->		
<div class="modal report-modal fade" style="display:none">
	 <div class="modal-dialog" role="document"  style="position: fixed; 
            top: 40%; left: 50%; transform: translate(-50%, -50%); 
            width: 100%; max-width: 750px;">
        <div class="modal-content"> <!-- 모달 내용으로 포함시킬 부분 -->
        <form action ="${pageContext.request.contextPath}/report/add" method="post" name="reportform" id="reportform">
        	<p class = "reportT">게시글 신고</p>
        	<br>
        	<input type="hidden" name="report_type" id ="report_type" value="A"> <!-- 신고유형 분류, 게시글은 A, 숨겨둠. -->
        	<input type="hidden" name="reporter" id="reporter" value="${idx}"> <!-- 신고자, 로그인 한 아이디로 가져옴. 회원 번호(idx)로 저장 -->
        	<input type="hidden" name="target" id="target" value="${postdata.idx}"> <!-- 신고당하는 사람, view에서 데이터 가져왔을때 그 객체에서 .idx 뽑아와야함-->
        	<input type="hidden" name="report_ref_id" id="report_ref_id" value="${postdata.post_id}"> <!-- 참조할 번호. A면 postid, B면 commentid, C면 matchid-->
        	<input type="hidden" name="post_id" class="post_id" value="${postdata.post_id}"> <!--신고할때 게시글 번호 가져가려고 값 저장해두는 postid -->
        	
        	<p class = "reportR">
        	<span class="redColor">★&nbsp;</span>신고사유</p>
        	
        	<!-- 신고사유 선택 = report: title 부분 -->
        	<div id="title"> <!-- select는 중앙으로 정렬이 안돼서 부모요소로 div 써둠 -->
			<select name="title" required>
				<option disabled selected hidden value="">신고 사유를 선택해 주세요</option>
				<option value="욕설, 혐오 표현 등이 포함된 글">욕설, 혐오 표현 등이 포함된 글</option>
				<option value="갈등 조장글">갈등 조장글</option>
				<option value="게시판과 관계 없는 내용">게시판과 관계 없는 내용</option>
				<option value="도배 목적의 글">도배 목적의 글</option>
				<option value="성적 컨텐츠가 포함된 글">성적 컨텐츠가 포함된 글</option>
				<option value="직접 입력">직접 입력</option>
			</select>
			</div>
			
			<br>
			<div id="content"> <!-- textarea는 속성으로 중앙에 위치시킬 수 없어서 부모요소로 쓴 div -->
				<textarea placeholder ="내용을 작성해 주세요." maxlength="300" name="content" required></textarea>
			</div>
			
			<br>
			
			<%--
			<!-- 차단 여부 묻는 부분 -->
			<div style="display:flex;  align-items: center; margin-left:150px">
				<input type="checkbox" style="margin-right:10px">
				<p style="text-align:center; margin:0;">해당 글의 게시자를 <span>차단</span>할까요?<p>
			</div>
			<br>
			--%>
			
			<div id="reportbutton">
				<button class="reportBtn">신고하기</button>
			</div>
			
			</form>
			
		</div> <!-- modal-content -->
	</div> <!-- modal-dialog -->
</div> <!-- 모달창 끝 -->
<script>
	
</script>

</body>
</html>