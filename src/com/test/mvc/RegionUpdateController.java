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
public class RegionUpdateController implements Controller
{
	private IRegionDAO regiondao;

	public void setRegiondao(IRegionDAO regiondao)
	{
		this.regiondao = regiondao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		String regionName = request.getParameter("regionName");
		String regionId = request.getParameter("regionId");
		
		
		
		
		try
		{
			Region region = new Region();
			
			region.setRegionName(regionName);
			region.setRegionId(regionId);
			
			regiondao.modify(region);
			
			mav.setViewName("redirect:regionlist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}
