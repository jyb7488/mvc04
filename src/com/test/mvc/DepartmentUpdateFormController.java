/*=========================================
 * 		SampleController.java
 *   	- 사용자 정의 컨트롤러 클래스
 *=========================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//	  사용자 정의 컨트롤러 클래스를 구현한다.
public class DepartmentUpdateFormController implements Controller
{
	private IDepartmentDAO deptdao;
	
	public void setDeptdao(IDepartmentDAO deptdao)
	{
		this.deptdao = deptdao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name")==null)
		{
			mav.setViewName("loginform.action");
			return mav;
		}else if(session.getAttribute("admin")==null)
		{
			mav.setViewName("logout.action");
			return mav;
		}
		
		
		String departmentId = request.getParameter("departmentId");
		
		try
		{
			Department departmentList =  deptdao.searchId(departmentId);
			
			mav.addObject("departmentList", departmentList);
			
			mav.setViewName("/WEB-INF/views/DepartmentUpdateForm.jsp");
						
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		return mav;
	}
}
