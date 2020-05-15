package com.project.emp.dto;

import java.text.SimpleDateFormat;

import com.project.emp.other.JsonPasing;

public abstract class AbstractDto {
    
    private JsonPasing<AbstractDto> json = new JsonPasing<AbstractDto>();
    //Abstarct처리
    public void dtoProc() {
        
    }

	//자동으로 dto를 json모델로 스트링 리턴하게 해주는 코드
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return json.ModelOnJson(this);
	}
	public String toStringType() {
        return this.getClass().getSimpleName();
    }
	
	//자동으로 dto의 date를 YYYYMMDD로 바꿔주는 코드 
    public String dateToString(java.sql.Date date) {
        // TODO Auto-generated method stub
        return date.toString().replaceAll("-", "");
    }
    
    public String dateToString(java.util.Date date) {
        // TODO Auto-generated method stub
        return dateToString(date, "YYYYMMDD");
    }
    
    //자동으로 dto의 date를 포맷 형식으로 바꿔주는 코드 
    public String dateToString(java.util.Date date, String format) {
        // TODO Auto-generated method stub
        return new SimpleDateFormat(format).format(date);
    }
	
	
}
