package com.project.emp.other;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import com.project.emp.dto.AbstractDto;

public class CodeMap {
	
	private static String noSpecialChaRegExp = "[!@#$%^&*(),.?\\\"\':{}|<>]";
	private static String numbericAndAlphabet = "^[0-9a-z]*$ {8}";
	
	public static final String AWS_PWD = "/resources/secure/sppro.pem";
	public static final String AWS_SERVER_HOST = "ec2-52-15-192-154.us-east-2.compute.amazonaws.com";
	public static final String AWS_FILE_DEFAULT_CD = "/home/ec2-user/sppro";
	
	/**
	 * 해당 문자가 숫자인지 확인시켜줌 숫자가 맞으면 true 아니면 false
	 * */
	public static boolean isNumberic(String number) {
		try {
		    for(char numberChar : number.toCharArray()) {
		        String oneNumber = String.valueOf(numberChar);
		        Integer.parseInt(oneNumber);
		    }
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	/**
     * 오브젝트 A와 B가 일치한지 비교
     * */
    public static boolean isEqual(Object A, Object B) {
       if(A==null && B==null) {
           return true;
       }
       if(A==null && B!=null) {
           return false;
       }
       if(A!=null && B==null) {
           return false;
       }
       return A.equals(B);
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
	    int argLength = 0;
	    if(argument!=null) {
	        argLength = argument.length();
	    }
	    if(argLength < length) {
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
     * 양 DTO의 값이 일치한지, 일치하면 true, 일치하지 않으면 false
     * @throws Exception 
    */
    public static <T extends AbstractDto> boolean isEqualDtoCompareTo(T aDto, T bDto) throws  Exception {
        String aDtoType = aDto.toStringType();
        //일단 타입이 일치해야함
        if(!aDtoType.equals(bDto.toStringType())) {
            return false;
        }
        //타입이 일치하였을 때
        Method[] aDtoMethods = aDto.getClass().getMethods();
        for(int index = 0 ; index < aDtoMethods.length ; index++) {
            if(!aDtoMethods[index].getName().startsWith("get")) {
                continue;
            }
            Object aValue = aDtoMethods[index].invoke(aDto);
            Object bValue = aDtoMethods[index].invoke(bDto);
            if(!isEqual(aValue, bValue)) {
                return false;
            }
        }
        return true;
    }


}
