package com.project.emp.dto;

import java.io.Serializable;

public class TestDto extends AbstractDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3650918262861836993L;
	
	/**
	 * Create Table TestTable(
	 * eid varchar(50) Primary Key,
	 * epw varchar(50),
	 * ereg_date date
	 * )
	 * 
	 * 샘플용 Dto
	 * */
	private String eid;
	private String epw;
	private String eregDate;
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getEpw() {
		return epw;
	}
	public void setEpw(String epw) {
		this.epw = epw;
	}
	public String getEregDate() {
		return eregDate;
	}
	public void setEregDate(String eregDate) {
		this.eregDate = eregDate;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[\n\"eid\" : " + eid + ",\n\"epw\" : "+ epw +",\n\"eregDate\" : " + eregDate +"\n]";
	}
	
	
	
	
}
