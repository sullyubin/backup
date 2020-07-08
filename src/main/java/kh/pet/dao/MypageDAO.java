package kh.pet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.pet.dto.MyPage_CommunityDTO;
import kh.pet.dto.Mypet_regDTO;
import kh.pet.dto.PointDTO;
import kh.pet.dto.RegLookupDTO;


@Repository
public class MypageDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;


	
	public void insert(Mypet_regDTO dto) {
		System.out.println("성별 : "+dto.getPet_gender());
		mybatis.insert("Pet_reg.pet_insert",dto);
	}
	
	
	public List<Mypet_regDTO> selectAll(String id) {
		List<Mypet_regDTO> list = mybatis.selectList("Pet_reg.list",id);
		return list;
	}
	
	public Mypet_regDTO petlist(int seq) {
		Mypet_regDTO dto = mybatis.selectOne("Pet_reg.petlist", seq);
		return dto;
	}
	
	public void petdel(int seq) {
		mybatis.delete("Pet_reg.petdel", seq);
	}
	
	
	public List<RegLookupDTO> reglookup(String id) {
		List<RegLookupDTO> list = mybatis.selectList("Pet_reg.lookup", id);
		return list;
	}
	
	public List<MyPage_CommunityDTO> community(String id) {
		List<MyPage_CommunityDTO> list = mybatis.selectList("Pet_reg.community", id);
		return list;
	}
	
	public List<PointDTO> pointlist(String id) {
		List<PointDTO> list = mybatis.selectList("Pet_reg.point", id);
		return list;
	}
	
	public void pointinsert(String id, int pointnum, String add, String resnum) {
		Map<String, Object> list = new HashMap<String, Object>();
		list.put("id", id);
		list.put("point", pointnum);
		list.put("deltype", add);
		list.put("resnum", resnum);
		mybatis.insert("Pet_reg.pointinsert", list);
	}
	
	
	
}
