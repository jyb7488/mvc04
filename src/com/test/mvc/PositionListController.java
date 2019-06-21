/*=========================================
 * 		SampleController.java
 *   	- 사용자 정의 컨트롤러 클래스
 *=========================================*/

package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//	  사용자 정의 컨트롤러 클래스를 구현한다.
public class PositionListController implements Controller
{
	private IPositionDAO positiondao;

	public void setPositiondao(IPositionDAO positiondao)
	{
		this.positiondao = positiondao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		try
		{
			ArrayList<Position> positionList = positiondao.list();
			
			mav.addObject("positionList", positionList);
			
			mav.setViewName("/WEB-INF/views/PositionList.jsp");			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		
		
		return mav;
	}
}
