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
<title>EmployeeList.jsp</title>
<link rel="stylesheet" href="<%=cp%>/css/main.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript">
	
	//$(document).ready();
	$(function()
	{
		$(".updateBtn").click(function()
		{
			// 테스트
			// alert("수정 버튼 클릭");
			
			$(location).attr("href","employeeupdateform.action?employeeId="+$(this).val());
			
		});
		
		$(".deleteBtn").click(function()
		{
			// 테스트
			// alert("삭제 버튼 클릭");
			
			if(confirm("현재 선택한 데이터를 정말 삭제하시겠습니까?"))
			{
				$(location).attr("href","employeedelete.action?employeeId="+$(this).val());
			}
			
		});		
	});
	
</script>
</head>
<body>

<!---------------------------------------------------------------------------
	#14. EmployeeList.jsp
	- 직원 데이터 출력 페이지
	- 관리자가 접근하는 직원 데이터 출력 페이지
	  (일반 직원이 접근하는 직원 데이터 출력 페이지 : EmpList.jsp 로 구성)
----------------------------------------------------------------------------->

<div>
	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		<h1>[직원관리]</h1>
		<hr />
		<div>
			<form>
				<input type="button" value="직원추가" class="btn" onclick="location.href='employeeinsertform.action'"/>
			</form>
		</div>
		
		<br><br>
		
		<!---------------------------------------------------------------------------------
			EMPLOYEEID NAME BIRTHDAY LUNAR TELEPHONE DEPARTMENTID POSITIONID REGIONID 
			BASICPAY EXTRAPAY SSN1 SSN2 GRADE       
		    
		    
		    
		    employeeId, name, ssn, birthday, lunarName, telephone, departmentId, departmentName, positionId,
			positionName, regionId, regionName
			
			윤비야 오늘 김밥 먹자
			오늘은 색깔 잘 보고 김치김밥이랑 참치김밥이랑 헷갈리지 말자
			배고파........................................................................		
		
		----------------------------------------------------------------------------------->
		<table id="customers" class="table">
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>주민번호</th>
				<th>생년월일</th>
				<th>양/음력</th>
				<th>전화번호</th>
				<th>지역</th>
				<th>부서</th>
				<th>직위</th>
				<th>기본급</th>
				<th>수당</th>
				<th>급여</th>
				<th>등급</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="employeeList" items="${employeeList }">
			<tr>
				<td>${employeeList.employeeId }</td>
				<td>${employeeList.name }</td>
				<td>${employeeList.ssn }-*******</td>
				<td>${employeeList.birthday }</td>
				<td>${employeeList.lunarName }</td>
				<td>${employeeList.telephone }</td>
				<td>${employeeList.regionName }</td>
				<td>${employeeList.departmentName }</td>
				<td>${employeeList.positionName }</td>
				
				<%-- <td>${employeeList.basicPay }</td> --%>
				<td>
					<fmt:formatNumber value="${employeeList.basicPay }" groupingUsed="true"></fmt:formatNumber>
				</td>
				
				<%-- <td>${employeeList.extraPay }</td> --%>
				<td>
					<fmt:formatNumber value="${employeeList.extraPay }" groupingUsed="true"></fmt:formatNumber>
				</td>
				
				<%-- <td>${employeeList.pay }</td> --%>
				<td>
					<fmt:formatNumber value="${employeeList.pay }" groupingUsed="true"></fmt:formatNumber>
				</td>
				
				<%-- 
				<c:choose>
					<c:when test="${employeeList.grade eq 0}"><td>관리자</td></c:when>
					<c:when test="${employeeList.grade eq 1}"><td>사원</td></c:when>
				</c:choose>
				 --%>
				<td>${employeeList.grade==0 ? "관리자" : "일반사원" }</td> 
				 
				<td><button type="button" class="btn updateBtn" value="${employeeList.employeeId }">수정</button></td>
				<td><button type="button" class="btn deleteBtn" value="${employeeList.employeeId }">삭제</button></td>
			</tr>
			</c:forEach>

				<!-- 				
				<td>1</td>
				<td>유진석</td>
				<td>631212</td>
				<td>1963-12-12</td>
				<td>양력</td>
				<td>010-4543-2342</td>
				<td>서울</td>
				<td>개발부</td>
				<td>사원</td>
				<td>1,500,000</td>
				<td>1,000,000</td>
				<td>2,500,000</td>
				<td>관리자</td>
				 -->
		</table>
	</div>
		<!-- 회사 소개 및 어플리케이션 소개 영역 -->
		<div id="footer">
		
		</div>	
</div>

</body>
</html>