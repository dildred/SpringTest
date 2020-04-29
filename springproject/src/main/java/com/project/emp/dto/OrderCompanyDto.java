package com.project.emp.dto;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 발주 회사 테이블
 */
@Alias("OrderCompany")
public class OrderCompanyDto extends AbstractDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4445482211976679397L;

    /**발주 회사 코드*/
    private String companyCd;
    /**발주 회사 명*/
    private String companyName;
    /**발주 회사 전화 번호*/
    private String companyTel;
    /**발주 회사 주소*/
    private String companyAddress;
    /**발주 회사 비고*/
    private String companyComment;
    
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyTel() {
        return companyTel;
    }
    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }
    public String getCompanyAddress() {
        return companyAddress;
    }
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    public String getCompanyComment() {
        return companyComment;
    }
    public void setCompanyComment(String companyComment) {
        this.companyComment = companyComment;
    }
    
    
}
