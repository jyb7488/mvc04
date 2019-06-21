<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EmpList.jsp</title>
<link rel="stylesheet" href="<%=cp%>/css/main.css">
</head>
<body>

<!---------------------------------------------------------------------------
	#31. EmpList.jsp
	- 일반 직원 전용 출력 페이지.
	- 로그아웃 버튼.
	- 입력 / 수정 / 삭제 기능 없음.
----------------------------------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		<h1>[ 직원 리스트 ]</h1>
		<hr />
		
		<br><br>
		
		<!---------------------------------------------------------------------------------
			EMPLOYEEID NAME DEPARTMENTNAME POSITIONNAME REGIONNAME GRADE
		----------------------------------------------------------------------------------->
				<!-- 				
				<td>1</td>
				<td>유진석</td>
				<td>서울</td>
				<td>개발부</td>
				<td>사원</td>
				<td>관리자</td>
				 -->
		<table id="customers" class="table">
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>지역</th>
				<th>부서</th>
				<th>직위</th>
				<th>등급</th>
			</tr>
			<c:forEach var="employeeList" items="${employeeList }">
			<tr>
				<td>${employeeList.employeeId }</td>
				<td>${employeeList.name }</td>
				<td>${employeeList.regionName }</td>
				<td>${employeeList.departmentName }</td>
				<td>${employeeList.positionName }</td>
				
				<td>${employeeList.grade==0 ? "관리자" : "일반사원" }</td> 
				 
			</tr>
			</c:forEach>

		</table>
	</div>
		<!-- 회사 소개 및 어플리케이션 소개 영역 -->
		<div id="footer">
		
		</div>	
</div>

</body>
</html>