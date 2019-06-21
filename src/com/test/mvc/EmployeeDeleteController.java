/*===============================================================================
 * 		#26. EmployeeDeleteController.java
 *   	- 사용자 정의 컨트롤러 클래스
 *   	- 직원 데이터 삭제 액션 수행 → employeelist.action 재요청 안내
 *   	- DAO 객체에 대한 의존성 주입(DI)을 위한 준비
 *   	  → 인터페이스 형태의 자료형을 속성으로 구성
 *        → setter 메소드 준비
 *===============================================================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EmployeeDeleteController implements Controller
{
	private IEmployeeDAO dao;
	
	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드
		
		ModelAndView mav = new ModelAndView();
		
		// 데이터 수신 (EmployeeList.jsp로 부터... employeeId 수신)
		String employeeId = request.getParameter("employeeId");
		
		try
		{
			dao.remove(employeeId);
			
			mav.setViewName("redirect:employeelist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		
		
		return mav;
	}
}
