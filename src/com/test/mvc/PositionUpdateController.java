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
public class PositionUpdateController implements Controller
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
		
		String positionName = request.getParameter("positionName");
		String positionId = request.getParameter("positionId");
		
		try
		{
			Position position = new Position();
			
			position.setPositionName(positionName);
			position.setPositionId(positionId);
			
			positiondao.modify(position);
			
			mav.setViewName("redirect:positionlist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}
