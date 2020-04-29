package com.project.emp.dto;

import java.io.Serializable;
import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("Staff")
public class StaffDto extends AbstractDto implements Serializable	{
		
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		//사번
		private String staff_number;
		//이름
		private String staff_name;
		//나이
		private String staff_age;
		//폰번호
		private String staff_tel;
		//주민번호
		private String staff_id_num;
		//이메일
		private String staff_email;
		//학력
		private String staff_end_degree;
		//부서
		private String staff_dep;
		//직급
		private String staff_level;
		//급여
		private String staff_pay;
		//주소
		private String staff_address;
		//삭제 플래그
		private Date staff_date;
		
		public String getStaff_number() {
			return staff_number;
		}
		public void setStaff_number(String staff_number) {
			this.staff_number = staff_number;
		}
		public String getStaff_name() {
			return staff_name;
		}
		public void setStaff_name(String staff_name) {
			this.staff_name = staff_name;
		}
		public String getStaff_age() {
			return staff_age;
		}
		public void setStaff_age(String staff_age) {
			this.staff_age = staff_age;
		}
		public String getStaff_tel() {
			return staff_tel;
		}
		public void setStaff_tel(String staff_tel) {
			this.staff_tel = staff_tel;
		}
		public String getStaff_id_num() {
			return staff_id_num;
		}
		public void setStaff_id_num(String staff_id_num) {
			this.staff_id_num = staff_id_num;
		}
		public String getStaff_email() {
			return staff_email;
		}
		public void setStaff_email(String staff_email) {
			this.staff_email = staff_email;
		}
		public String getStaff_end_degree() {
			return staff_end_degree;
		}
		public void setStaff_end_degree(String staff_end_degree) {
			this.staff_end_degree = staff_end_degree;
		}
		public String getStaff_dep() {
			return staff_dep;
		}
		public void setStaff_dep(String staff_dep) {
			this.staff_dep = staff_dep;
		}
		public String getStaff_level() {
			return staff_level;
		}
		public void setStaff_level(String staff_level) {
			this.staff_level = staff_level;
		}
		public String getStaff_pay() {
			return staff_pay;
		}
		public void setStaff_pay(String staff_pay) {
			this.staff_pay = staff_pay;
		}
		public String getStaff_address() {
			return staff_address;
		}
		public void setStaff_address(String staff_address) {
			this.staff_address = staff_address;
		}
		public Date getStaff_date() {
			return staff_date;
		}
		public void setStaff_date(Date staff_date) {
			this.staff_date = staff_date;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
		
		
		
		
		
	
		
		
				
		
	
}
