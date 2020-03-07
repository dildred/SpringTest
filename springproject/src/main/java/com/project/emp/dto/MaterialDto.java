package com.project.emp.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * 재료 등록 테이블
 */
public class MaterialDto extends AbstractDto implements Serializable  {
	
	private static final long serialVersionUID = 2053187505393042042L;
	
	/**재료 번호*/
	private String matNo;
	/**재료 이름*/
	private String matName;
	/**재료 중량단위*/
	private String weightUnit;
	/**재료 분류*/
	private String matStatus;
	/**삭제 플래그*/
	private String delFlg;
	/**등록 일자*/
	private Date registDate;
	/**등록 유저*/
	private String registUser;
	/**변경 일자*/
	private Date updateDate;
	/**변경 유저*/
	private String updateUser;
	/**변경 횟수*/
	private String updateCnt;
	
	
	public String getMatNo() {
		return matNo;
	}
	public void setMatNo(String matNo) {
		this.matNo = matNo;
	}
	public String getMatName() {
		return matName;
	}
	public void setMatName(String matName) {
		this.matName = matName;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public String getMatStatus() {
		return matStatus;
	}
	public void setMatStatus(String matStatus) {
		this.matStatus = matStatus;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	public String getRegistUser() {
		return registUser;
	}
	public void setRegistUser(String registUser) {
		this.registUser = registUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateCnt() {
		return updateCnt;
	}
	public void setUpdateCnt(String updateCnt) {
		this.updateCnt = updateCnt;
	}
	
	
	
}
