package com.project.emp.other;

import java.util.regex.Pattern;

public class CodeMap {
	
	private static String noSpecialChaRegExp = "[!@#$%^&*(),.?\\\"\':{}|<>]";
	
	/**
	 * 해당 문자가 숫자인지 확인시켜줌 숫자가 맞으면 true 아니면 false
	 * */
	public static boolean isNumberic(String number) {
		try {
			Integer.parseInt(number);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	/**
	 *  해당 문자가 비어있는지 확인
	*/
	public static boolean isEmpty(String argument) {
		// TODO Auto-generated method stub
		if(argument==null) {
			return true;
		}
		if(argument.isEmpty()) {
			return true;
		}
		if(argument.trim().equals("")){
			return true;
		}
		return false;
	}
	
	/**
	 * 해당 문자에 특수문자가 포함되어 있다면 true 포함되어 있지 않다면 false
	*/
	public static boolean isSpecialChaReg(String argument) {
		if(Pattern.matches(noSpecialChaRegExp, argument)) {
			return true;
		}
		return false;
	}

}
