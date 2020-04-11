package com.project.emp.dto;

import java.io.Serializable;
import java.sql.Date;


public class StaffDto extends AbstractDto implements Serializable	{
		
		private static final long serialVersionUID = 1L;
		
		//사번
		private String staffnumber;
		//이름
		private String name;
		//나이
		private String age;
		//직급
		private String position;
		//폰번호
		private String phonenumber;
		//부서
		private String department;
		//급여
		private String pay;
		//학력
		private String education;
		//이메일
		private String email;
		//주소
		private String address;
		//가입날짜
		private Date registDate;
		
		
		
		public String getStaffnumber() {
			return staffnumber;
		}
		public void setStaffnumber(String staffnumber) {
			this.staffnumber = staffnumber;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getPhonenumber() {
			return phonenumber;
		}
		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getPay() {
			return pay;
		}
		public void setPay(String pay) {
			this.pay = pay;
		}
		public String getEducation() {
			return education;
		}
		public void setEducation(String education) {
			this.education = education;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public Date getRegistDate() {
			return registDate;
		}
		public void setRegistDate(Date registDate) {
			this.registDate = registDate;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		
		
				
		
	
}
