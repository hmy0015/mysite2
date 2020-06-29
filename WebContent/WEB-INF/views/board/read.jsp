<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite2/assets/css/mysite.css" rel="stylesheet"
	type="text/css">
<link href="/mysite2/assets/css/board.css" rel="stylesheet"
	type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- //header -->
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<!-- //nav -->
		<c:import url="/WEB-INF/views/include/nav.jsp"></c:import>

		<!-- //aside -->
		<c:import url="/WEB-INF/views/include/asideUser.jsp"></c:import>
		<div id="content">

			<div id="content-head">
				<h3>�Խ���</h3>
				<div id="location">
					<ul>
						<li>Ȩ</li>
						<li>�Խ���</li>
						<li class="last">�ϹݰԽ���</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="read">
					<form action="#" method="get">
						<!-- �ۼ��� -->
						<div class="form-group">
							<span class="form-text">�ۼ���</span> <span class="form-value">���켺</span>
						</div>

						<!-- ��ȸ�� -->
						<div class="form-group">
							<span class="form-text">��ȸ��</span> <span class="form-value">123</span>
						</div>

						<!-- �ۼ��� -->
						<div class="form-group">
							<span class="form-text">�ۼ���</span> <span class="form-value">2020-03-02</span>
						</div>

						<!-- ���� -->
						<div class="form-group">
							<span class="form-text">�� ��</span> <span class="form-value">
								���⿡�� �������� ��µ˴ϴ�.</span>
						</div>

						<!-- ���� -->
						<div id="txt-content">
							<span class="form-value"> 
								���⿡�� ���������� ��µ˴ϴ�.<br> 
								���⿡�� ���������� ��µ˴ϴ�.<br>
							</span>
						</div>

						<a id="btn_modify" href="">����</a> <a id="btn_modify" href="">���</a>

					</form>
					<!-- //form -->
				</div>
				<!-- //read -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>
		<!-- //footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
</body>
</html>