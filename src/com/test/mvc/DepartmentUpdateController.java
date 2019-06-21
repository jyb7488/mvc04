/*=========================================
 * 		SampleController.java
 *   	- 사용자 정의 컨트롤러 클래스
 *=========================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//	  사용자 정의 컨트롤러 클래스를 구현한다.
public class DepartmentUpdateController implements Controller
{
	private IDepartmentDAO deptdao;
	
	public void setDeptdao(IDepartmentDAO deptdao)
	{
		this.deptdao = deptdao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		String departmentName = request.getParameter("departmentName");
		String departmentId = request.getParameter("departmentId");
		
		try
		{
			Department department = new Department();
			
			department.setDepartmentName(departmentName);
			department.setDepartmentId(departmentId);
			
			deptdao.modify(department);
			
			mav.setViewName("redirect:departmentlist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}
