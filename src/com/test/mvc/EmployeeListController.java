/*=========================================
 * 		#15. EmployeeListController.java
 *   	- 사용자 정의 컨트롤러 클래스
 *   	- 리스트 페이지 요청에 대한 액션 처리
 *   	- DAO 객체에 대한 의존성 주입(DI)을 위한 준비
 *        → 인터페이스 형태의 자료형을 속성으로 구성
 *        → setter 메소드 준비
 *=========================================*/

package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EmployeeListController implements Controller
{
	// DAO 인터페이스 자료형 멤버 구성
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

		// 세션 처리 과정 추가 ------------------------------------------------------------------------------------------------
		HttpSession session = request.getSession();
		
		if (session.getAttribute("name")==null)		//-- 로그인이 되어있지 않은 상황
		{
			mav.setViewName("redirect:loginform.action");
			return mav;
		}else if(session.getAttribute("admin")==null)	//-- 관리자 아님. 일반 사원.
		{
			mav.setViewName("redirect:logout.action");
			return mav;
		}
		
		// ------------------------------------------------------------------------------------------------ 세션 처리 과정 추가 
		
		
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		try
		{
			employeeList = dao.list();
			
			mav.addObject("employeeList",employeeList);
			
			mav.setViewName("WEB-INF/views/EmployeeList.jsp");
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		return mav;
	}
}
