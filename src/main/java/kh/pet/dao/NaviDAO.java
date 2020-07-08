package kh.pet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.pet.staticInfo.Main_Configuration;;

@Repository
public class NaviDAO {

	@Autowired
	private SqlSessionTemplate mybaits;


	// 게시판에 올라갈 전체 개수
	public int getArticleCount(String name) throws Exception {
		String id="";
		if(name.contentEquals("Navi.bar")) {
			id="aa";
		}else if(name.contentEquals("Navi.pointcount")) {
			id="aa";
		}
		int result = mybaits.selectOne(name, id);
		System.out.println(result);
		return result;
	}

	//시작페이지 종료페이지 정하는 로직
	public List<Object> selectByPageNo(int cpage, String id, String name) throws Exception {
		if(name.contentEquals("list")) {
			Main_Configuration.recordCountPerPage=6;
			Main_Configuration.naviCountPerPage=5;
		}else if(name.contentEquals("listpoint")) {
			Main_Configuration.recordCountPerPage=10;
			Main_Configuration.naviCountPerPage=10;
		}
		int start = cpage * Main_Configuration.recordCountPerPage - (Main_Configuration.recordCountPerPage - 1);
		int end = start + (Main_Configuration.recordCountPerPage - 1);
		Map<String, Object> se = new HashMap<String, Object>();
		se.put("start", start);
		se.put("end", end);
		se.put("id", id);
		if(name.contentEquals("list")) {
		List<Object> list = mybaits.selectList("Navi.navibar", se);
		return list;
		}else if(name.contentEquals("listpoint")) {
		List<Object> list = mybaits.selectList("Navi.pointbar", se);
		System.out.println("listpoint 리턴");
		return list;
		}
		return null ;
	}

// 네비바 만드는 메소드
	public String getPageNavi(int currentPage, String name) throws Exception {
		int recordTotalCount=0;
		if(name.contentEquals("list")) {
			recordTotalCount = getArticleCount("Navi.bar");
		}else if(name.contentEquals("listpoint")) {
			recordTotalCount = getArticleCount("Navi.pointcount");
		}
		int pageTotalCount = 0;
		if ((recordTotalCount % Main_Configuration.recordCountPerPage) != 0) {
			pageTotalCount = (recordTotalCount / Main_Configuration.recordCountPerPage) + 1;
		} else {
			pageTotalCount = (recordTotalCount / Main_Configuration.recordCountPerPage);
		}
		System.out.println("네비바 개수 : " + pageTotalCount);
		// 공격자를 대비한 보안 코드
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int startNavi = (currentPage - 1) / Main_Configuration.naviCountPerPage * Main_Configuration.naviCountPerPage + 1;

		int endNavi = startNavi + Main_Configuration.naviCountPerPage - 1;

		if (pageTotalCount < endNavi) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;

		StringBuilder sb = new StringBuilder();

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		if (needPrev) {
			sb.append("<a href='"+name+"?cpage=" + (startNavi - 1) + "'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {

			sb.append("<a href='"+name+"?cpage=" + i + "'>" + i + " " + "</a>");
		}
		if (needNext) {
			sb.append("<a href='"+name+"?cpage=" + (endNavi + 1) + "'> > </a>");
		}
		return sb.toString();
	}
}
