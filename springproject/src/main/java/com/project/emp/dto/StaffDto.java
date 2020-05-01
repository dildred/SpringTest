package com.project.emp.dto;

import java.io.Serializable;
import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("Staff")
public class StaffDto extends AbstractDto implements Serializable	{
		
	
	private static final long serialVersionUID = 1L;
		
		//사번
		private String staffNumber;
		//이름
		private String staffName;
		//나이
		private String staffAge;
		//폰번호
		private String staffTel;
		//주민번호
		private String staffId_num;
		//이메일
		private String staffEmail;
		//학력
		private String staffEnd_degree;
		//부서
		private String staffDep;
		//직급
		private String staffLevel;
		//급여
		private String staffPay;
		//주소
		private String staffAddress;
		//삭제 플래그 <-이거땜인가?
		private Date staffDate;
		
		
		
		public String getStaffNumber() {
			return staffNumber;
		}
		public void setStaffNumber(String staffNumber) {
			this.staffNumber = staffNumber;
		}
		public String getStaffName() {
			return staffName;
		}
		public void setStaffName(String staffName) {
			this.staffName = staffName;
		}
		public String getStaffAge() {
			return staffAge;
		}
		public void setStaffAge(String staffAge) {
			this.staffAge = staffAge;
		}
		public String getStaffTel() {
			return staffTel;
		}
		public void setStaffTel(String staffTel) {
			this.staffTel = staffTel;
		}
		public String getStaffId_num() {
			return staffId_num;
		}
		public void setStaffId_num(String staffId_num) {
			this.staffId_num = staffId_num;
		}
		public String getStaffEmail() {
			return staffEmail;
		}
		public void setStaffEmail(String staffEmail) {
			this.staffEmail = staffEmail;
		}
		public String getStaffEnd_degree() {
			return staffEnd_degree;
		}
		public void setStaffEnd_degree(String staffEnd_degree) {
			this.staffEnd_degree = staffEnd_degree;
		}
		public String getStaffDep() {
			return staffDep;
		}
		public void setStaffDep(String staffDep) {
			this.staffDep = staffDep;
		}
		public String getStaffLevel() {
			return staffLevel;
		}
		public void setStaffLevel(String staffLevel) {
			this.staffLevel = staffLevel;
		}
		public String getStaffPay() {
			return staffPay;
		}
		public void setStaffPay(String staffPay) {
			this.staffPay = staffPay;
		}
		public String getStaffAddress() {
			return staffAddress;
		}
		public void setStaffAddress(String staffAddress) {
			this.staffAddress = staffAddress;
		}
		public Date getStaffDate() {
			return staffDate;
		}
		public void setStaffDate(Date staffDate) {
			this.staffDate = staffDate;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		

		
		
		
	
		
		
				
		
	
}
