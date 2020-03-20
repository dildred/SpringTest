package com.project.emp.other;

public class CodeMap {
	
	//해당 문자가 숫자인지 확인시켜줌 숫자가 맞으면 true 아니면 false
	public static boolean isNumberic(String number) {
		try {
			Integer.parseInt(number);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

}
