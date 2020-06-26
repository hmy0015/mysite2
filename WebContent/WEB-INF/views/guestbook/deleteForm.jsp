<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	int no = Integer.parseInt(request.getParameter("no"));
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite2/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<link href="/mysite2/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
</head>

<body>
	<div id="wrap">

		<!-- //header -->
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

		<!-- //nav -->
		<jsp:include page="/WEB-INF/views/include/nav.jsp"></jsp:include>

		<!-- //aside -->
		<jsp:include page="/WEB-INF/views/include/asideUser.jsp"></jsp:include>

		<div id="content">
			
			<div id="content-head">
            	<h3>일반방명록</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>방명록</li>
            			<li class="last">일반방명록</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="guestbook">
				<form action="/mysite2/guest" method="">
					<table id="guestDelete">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 25%;">
							<col style="width: 25%;">
						</colgroup>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
							<td class="text-left"><button type="submit">삭제</button></td>
							<td><a href="/mysite2/guest?action=addList">[메인으로 돌아가기]</a></td>
						</tr>
					</table>
					<input type='hidden' name="no" value=<%=no%>>
					<input type='hidden' name="action" value="delete">
				</form>
				
			</div>
			<!-- //guestbook -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		
		<!-- //footer -->
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>

	</div>
	<!-- //wrap -->

</body>

</html>