<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RegionList.jsp</title>
<link rel="stylesheet" href="<%=cp %>/css/main.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style type="text/css">
	.disable
	{
		color: gray;
	}
	
</style>

<script type="text/javascript">

	$(function()
	{
		$(".deleteBtn").click(function()
		{
			// 테스트
			//alert("삭제 버튼 클릭");
			
			if(confirm("현재 선택한 데이터를 삭제하시겠습니까?"))
			{
				$(location).attr("href", "regiondelete.action?regionId=" + $(this).val());
			}
			
		});
		
		$(".updateBtn").click(function()
		{
			// 테스트
			//alert("수정 버튼 클릭");
			
			$(location).attr("href", "regionupdateform.action?regionId=" + $(this).val());
		});
	});

</script>

</head>
<body>

<!---------------------------------------------------------------------------
	#34. RegionList.jsp  
	- 지역 데이터 출력 페이지(리스트 페이지)
	- 지역 추가 버튼 구성
	- 리스트 상에 수정 및 삭제 버튼 구성
----------------------------------------------------------------------------->

<div>

	<!-- 메뉴 영역 -->
	<div>
		<c:import url="EmployeeMenu.jsp"></c:import>
	</div>
	
	<!-- 콘텐츠 영역 -->
	<div id="content">
		
		<h1>[지역관리]</h1>
		<hr />
		<div>
			<form action="regioninsertform.action" method="post" id="regionForm">
				<input type="button" value="지역추가" class="btn" onclick="location.href='regioninsertform.action'">
			</form>			
		</div>
		
		<br><br>
		
		<!-- 
			REGIONID	REGIONNAME
		 -->
		 
		 <table id="customers" class="table">
		 	<tr>
		 		<th>번호</th>
		 		<th>지역명</th>
		 		<th>수정</th>
		 		<th>삭제</th>
		 	</tr>
		 	<!-- 
		 	<tr>
		 		<td>1</td>
		 		<td>서울</td>
		 		<td>
		 			<button type="button" class="btn">수정</button>
		 		</td>
		 		<td>
		 			<button type="button" class="btn">삭제</button>
		 		</td>
		 	</tr>
		 	 -->
			
			<c:forEach var="region" items="${regionList }">
			<tr>
				<td>
					${region.regionId }
				</td>
				<td>
					${region.regionName }
				</td>
				
				<td>
					<button type="button" class="btn updateBtn" value="${region.regionId }">수정</button>
				</td>


				<%-- 
					<td>
				<c:choose>
					<c:when test="${region.delCheck eq 0 } ">
						<button type="button" class="btn deleteBtn" >삭제</button>
					</c:when>
				
					<c:otherwise>
						<button type="button" class="disable">삭제</button>
					</c:otherwise>
				</c:choose>
					</td>
				  --%>
				 
				 
				
				<td>
					<button type="button" ${region.delCheck==0? "class='btn deleteBtn'" : "class='disable'" } 
										  ${region.delCheck==0? "" : "disabled='disabled'" } value="${region.regionId }">삭제</button>
				</td>
				
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