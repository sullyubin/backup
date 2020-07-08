package kh.pet.dto;

import java.sql.Timestamp;

public class PointDTO {
	private String id;
	private String deltype;
	private Timestamp day;
	private int point;
	private String resnum;

	public PointDTO() {}
	public PointDTO(String id, String deltype, Timestamp day, int point, String resnum) {
		super();
		this.id = id;
		this.deltype = deltype;
		this.day = day;
		this.point = point;
		this.resnum = resnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeltype() {
		return deltype;
	}

	public void setDeltype(String deltype) {
		this.deltype = deltype;
	}

	public Timestamp getDay() {
		return day;
	}

	public void setDay(Timestamp day) {
		this.day = day;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getResnum() {
		return resnum;
	}

	public void setResnum(String resnum) {
		this.resnum = resnum;
	}

}
