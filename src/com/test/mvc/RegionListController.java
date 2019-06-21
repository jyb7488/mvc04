/*===========================================================
 * 		#33. RegionListController.java
 *   	- 사용자 정의 컨트롤러 클래스
 *   	- 직원 데이터 리스트 페이지 요청에 대한 액션 처리
 *   	- DAO 객체에 대한 의존성 주입(DI)을 위한 준비
 *   	  → 인터페이스 형태의 자료형을 속성으로 구성
 *        → setter 메소드 준비
 *===========================================================*/

package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//	  사용자 정의 컨트롤러 클래스를 구현한다.
public class RegionListController implements Controller
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
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name")==null)
		{
			mav.setViewName("loginform.action");
			return mav;
		}
		else if(session.getAttribute("admin")==null)
		{
			mav.setViewName("logout.action");
			return mav;
		}
		
		
		ArrayList<Region> regionList = new ArrayList<Region>();
		
		try
		{
			regionList = regiondao.list();
			mav.addObject("regionList", regionList);
			mav.setViewName("/WEB-INF/views/RegionList.jsp");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		return mav;
	}
}
