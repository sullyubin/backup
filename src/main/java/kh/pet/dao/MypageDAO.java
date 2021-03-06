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
	
	public int pointcount(String id) {
		int counts=0;
		int minus = 0;
		Map<String, String> list = new HashMap<String, String>();
		list.put("minus", "사용");
		list.put("minus1", "환전");
		list.put("id", id);
		List<Integer> count = mybatis.selectList("Pet_reg.pointcount",id);
		for(int i = 0; i<count.size(); i++) {
			counts += count.get(i); 
		}
		List<Integer> pointminus = mybatis.selectList("Pet_reg.pointminus", list);
		for(int i = 0; i<pointminus.size(); i++) {
			minus += pointminus.get(i);
		}
		System.out.println("전체 : "+counts);
		System.out.println("마이스너스 : "+minus);
		return counts-minus;
	}
	
	
	
	public void insert(Mypet_regDTO dto) {
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
	
	public String typecheck(String id) {
		String mem_type = mybatis.selectOne("Pet_reg.typecheck",id);
		return mem_type;
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
	
	public void pointinsert(String id, int pointnum, String add, String mem_type) {
		Map<String, Object> list = new HashMap<String, Object>();
		list.put("p_id", id);
		list.put("p_point", pointnum);
		list.put("p_method", add);
		list.put("mem_type", mem_type);
		mybatis.insert("Pet_reg.pointinsert", list);
	}
	
	
	
}
