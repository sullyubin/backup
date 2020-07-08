package kh.pet.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.pet.dao.MypageDAO;
import kh.pet.dto.MyPage_CommunityDTO;

import kh.pet.dto.RegLookupDTO;
import kh.pet.service.Pet_listService;
import kh.pet.service.PointService;

@Controller
@RequestMapping("/mypage/")
public class MypageController {

	int sum = 0;
	
	@Autowired
	MypageDAO mdao;

	@Autowired
	HttpSession session;

	@Autowired
	Pet_listService plistservice;
	
	@Autowired
	PointService pointservice;

	@RequestMapping("mypage")
	public String main() {
		session.setAttribute("id", "aa");
		return "/mypage/mypage-main";
	}

	@RequestMapping("mypageuse")
	public String mypageuse(HttpServletRequest request) {
		List<RegLookupDTO> list = mdao.reglookup("aa");
		request.setAttribute("list", list);
		return "/mypage/Mypage-Use";
	}
	
	@RequestMapping("mypet")
	public String mypet() {
		return "/mypage/mypage-reservation";
	}
	
	@RequestMapping("community")
	public String community(HttpServletRequest request) {
		List<MyPage_CommunityDTO> list = mdao.community("aa");
		request.setAttribute("list", list);
		return "/mypage/mypage-community";
	}

	@RequestMapping("Introduction")
	public String Introduction(HttpServletRequest request) {
		return "/mypage/mypage-Introduction";
	}

	@RequestMapping("pay")
	public String pay(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("mem_point")); 
		System.out.println(num);
		request.setAttribute("num", num);
		return "/mypage/payment";
	}
	
	@RequestMapping("listpoint")
	public String listpoint(HttpServletRequest request) {
		try {
			int cpage = 1;
			try {
				cpage = Integer.parseInt(request.getParameter("cpage"));
			} catch (Exception e) {
			}
			System.out.println("현재 페이지 :" + cpage);
			List<Object> bdto = plistservice.selectByPageNo(cpage, "aa", "listpoint");
			String navi = plistservice.getPageNavi(cpage,"listpoint");
			request.setAttribute("navi", navi);
			request.setAttribute("bdto", bdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/mypage/mypage-point";
	}
	
	
	@RequestMapping("Conversion")
	public String Conversion(HttpServletRequest request) {
		String mem_point = request.getParameter("mem_point");
		int pointnum = pointservice.Pointexchange(mem_point);
		if(pointnum != 0) {
			mdao.pointinsert("aa",pointnum,"입금","예약신청해주세요");
		}
		request.setAttribute("pointnum", pointnum);
		return "/mypage/payment";
	}

	@RequestMapping("list")
	public String reservation(HttpServletRequest request) {
		try {
			int cpage = 1;
			try {
				cpage = Integer.parseInt(request.getParameter("cpage"));
			} catch (Exception e) {
			}
			System.out.println("현재 페이지 :" + cpage);
			List<Object> bdto = plistservice.selectByPageNo(cpage, "aa", "list");
			String navi = plistservice.getPageNavi(cpage,"list");
			request.setAttribute("navi", navi);
			request.setAttribute("bdto", bdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/mypage/mypage-list";
	}

	@RequestMapping("registration")
	public String registration() {
		return "/mypage/mypet-registration";
	}

}
