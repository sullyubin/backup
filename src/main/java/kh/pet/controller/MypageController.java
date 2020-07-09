package kh.pet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	private MypageDAO mdao;

	@Autowired
	HttpSession session;

	@Autowired
	private Pet_listService plistservice;

	@Autowired
	private PointService pointservice;

	@RequestMapping("mypage")
	public String main() {
		session.setAttribute("id", "1");
		return "mypage/mypage-main";
	}

	@RequestMapping("mypageuse")
	public String mypageuse(HttpServletRequest request) {
		List<RegLookupDTO> list = mdao.reglookup("aa");
		request.setAttribute("list", list);
		return "mypage/Mypage-Use";
	}

	@RequestMapping("mypet")
	public String mypet() {
		return "mypage/mypage-reservation";
	}

	@RequestMapping("community")
	public String community(HttpServletRequest request) {
		List<MyPage_CommunityDTO> list = mdao.community("aa");
		request.setAttribute("list", list);
		return "mypage/mypage-community";
	}

	@RequestMapping("Introduction")
	public String Introduction(HttpServletRequest request) {
		return "mypage/mypage-Introduction";
	}

	@RequestMapping("pay")
	public String pay(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("mem_point"));
		System.out.println(num);
		request.setAttribute("num", num);
		return "mypage/payment";
	}

	@RequestMapping("listpoint")
	public String listpoint(HttpServletRequest request) {
		try {
			int cpage = 1;
			try {
				cpage = Integer.parseInt(request.getParameter("cpage"));
			} catch (Exception e) {
			}
			String id = (String) session.getAttribute("id");
			System.out.println("현재페이지:" + cpage);
			List<Object> bdto = plistservice.selectByPageNo(cpage, id, "listpoint");
			String navi = plistservice.getPageNavi(cpage, "listpoint");
			System.out.println("컨트롤 : "+navi);
			String mem_type = mdao.typecheck(id);
			int sum = mdao.pointcount(id);
			request.setAttribute("navi", navi);
			request.setAttribute("bdto", bdto);
			request.setAttribute("mem_type", mem_type);
			request.setAttribute("sum", sum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mypage/mypage-point";
	}

	int pointnum = 0;

	@RequestMapping("Conversion")
	public String Conversion(HttpServletRequest request) {
		String mem_point = request.getParameter("mem_point");
		pointnum = pointservice.Pointexchange(mem_point);
		if (pointnum != 0) {
			request.setAttribute("pointnum", pointnum);
			return "mypage/payment";
		}
		return "mypage/Introduction";
	}

	@RequestMapping("pointadd")
	public void pointadd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = (String) session.getAttribute("id");
		String mem_type = mdao.typecheck(id);
		mdao.pointinsert(id, pointnum, "입금", mem_type);
		response.sendRedirect("listpoint");
	}

	@RequestMapping("list")
	public String reservation(HttpServletRequest request) {
		String id = (String)session.getAttribute("id");
		try {
			int cpage = 1;
			try {
				cpage = Integer.parseInt(request.getParameter("cpage"));
			} catch (Exception e) {
			}
			System.out.println("현재페이지 :" + cpage);
			List<Object> bdto = plistservice.selectByPageNo(cpage, id, "list");
			String navi = plistservice.getPageNavi(cpage, "list");
			request.setAttribute("navi", navi);
			request.setAttribute("bdto", bdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mypage/mypage-list";
	}

	@RequestMapping("registration")
	public String registration() {
		return "mypage/mypet-registration";
	}

}
