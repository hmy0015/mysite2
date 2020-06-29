<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite2/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite2/assets/css/board.css" rel="stylesheet" type="text/css">

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
				<div id="list">
					<form action="" method="">
						<div class="form-group text-right">
							<input type="text">
							<button type="submit" id=btn_search>�˻�</button>
						</div>
					</form>
					<table >
						<thead>
							<tr>
								<th>��ȣ</th>
								<th>����</th>
								<th>�۾���</th>
								<th>��ȸ��</th>
								<th>�ۼ���</th>
								<th>����</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>123</td>
								<td class="text-left"><a href="#">�Խ��� �Խñ��Դϴ�.</a></td>
								<td>���켺</td>
								<td>1232</td>
								<td>2020-12-23</td>
								<td><a href="">[����]</a></td>
							</tr>
							<tr>
								<td>123</td>
								<td class="text-left"><a href="#">�Խ��� �Խñ��Դϴ�.</a></td>
								<td>���켺</td>
								<td>1232</td>
								<td>2020-12-23</td>
								<td><a href="">[����]</a></td>
							</tr>
							<tr>
								<td>123</td>
								<td class="text-left"><a href="#">�Խ��� �Խñ��Դϴ�.</a></td>
								<td>���켺</td>
								<td>1232</td>
								<td>2020-12-23</td>
								<td><a href="">[����]</a></td>
							</tr>
							<tr>
								<td>123</td>
								<td class="text-left"><a href="#">�Խ��� �Խñ��Դϴ�.</a></td>
								<td>���켺</td>
								<td>1232</td>
								<td>2020-12-23</td>
								<td><a href="">[����]</a></td>
							</tr>
							<tr class="last">
								<td>123</td>
								<td class="text-left"><a href="#">�Խ��� �Խñ��Դϴ�.</a></td>
								<td>���켺</td>
								<td>1232</td>
								<td>2020-12-23</td>
								<td><a href="">[����]</a></td>
							</tr>
						</tbody>
					</table>
		
					<div id="paging">
						<ul>
							<li><a href="">��</a></li>
							<li><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">4</a></li>
							<li class="active"><a href="">5</a></li>
							<li><a href="">6</a></li>
							<li><a href="">7</a></li>
							<li><a href="">8</a></li>
							<li><a href="">9</a></li>
							<li><a href="">10</a></li>
							<li><a href="">��</a></li>
						</ul>
						
						
						<div class="clear"></div>
					</div>
					<a id="btn_write" href="">�۾���</a>
				
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<!-- //footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->

</body>

</html>
l>