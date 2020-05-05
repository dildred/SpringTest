package com.project.emp.other;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.project.emp.dto.MaterialOrderDto;

public class CodeMap {
	
	private static String noSpecialChaRegExp = "[!@#$%^&*(),.?\\\"\':{}|<>]";
	private static String numbericAndAlphabet = "^[0-9a-z]*$ {8}";
	
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
     *  해당 리스트가 비어있는지 확인
    */
    public static boolean isEmpty(@SuppressWarnings("rawtypes") List list) {
        if(list==null) {
            return true;
        }
        if(list.isEmpty()) {
            return true;
        }
        return false;
    }
    
    /**
     *  해당 날짜가 비어있는지 확인
    */
    public static boolean isEmpty(java.util.Date date) {
        if(date==null) {
            return true;
        }
        return false;
    }
    public static boolean isEmpty(java.sql.Date date) {
        if(date==null) {
            return true;
        }
        return false;
    }
    
    /**
     *  해당 숫자값이 비어있는지 확인
    */
    public static boolean isEmpty(Integer integer) {
        if(integer==null) {
            return true;
        }
        return false;
    }
    
    /**
     *  해당 금액적 요소가 비어있는지 확인
    */
    public static boolean isEmpty(BigDecimal bigDecimal) {
        if(bigDecimal==null) {
            return true;
        }
        return false;
    }
	
	/**
     *  해당 문자의 개수가 length이하인지 확인 이하가 맞으면 true 틀리면 false
    */
	public static boolean isLengthShortest(String argument, Integer length) {
	    if(argument.length() < length) {
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
	
	

    /**
     * 해당 문자가 숫자와 소문자로만 구성되어 있다면 true 그렇지 않다면 false
    */
    public static boolean isNumbericAndAlphabet(String argument) {
        if(Pattern.matches(numbericAndAlphabet, argument)) {
            return true;
        }
        return false;
    }
    
    /**
     * DB 트랜잭션 롤백
    */
    public static void rollback() {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }



}
