package com.project.emp.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import org.apache.ibatis.type.Alias;

/**
 * 발주 이력 등록 테이블
 */
@Alias("MatOrder")
public class MaterialOrderDto extends AbstractDto implements Serializable  {

    /**
     * 
     */
    private static final long serialVersionUID = 6589531694670552067L;
    
    /**발주 주문 번호*/
    private String orderCd;
    /**발주 내력 번호*/
    private Integer orderNo;
    /**재료 번호*/
    private String matNo;
    /**재료 이름*/
    private String matName;
    /**주문 수량*/
    private Integer orderQty;
    /**발주 날짜*/
    private Date orderDate;
    /**발주 금액*/
    private BigDecimal orderBill;
    /**발주 회사 명*/
    private String companyName;
    /**발주 회사 코드*/
    private String companyCd;
    /**발주 회사 전화번호*/
    private String companyTel;
    /**발주 회사 주소*/
    private String companyAddress;
    /**발주 코멘트*/
    private String orderComment;
    public String getOrderCd() {
        return orderCd;
    }
    public void setOrderCd(String orderCd) {
        this.orderCd = orderCd;
    }
    public Integer getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
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
    public Integer getOrderQty() {
        return orderQty;
    }
    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public BigDecimal getOrderBill() {
        return orderBill;
    }
    public void setOrderBill(BigDecimal orderBill) {
        this.orderBill = orderBill;
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
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getOrderComment() {
        return orderComment;
    }
    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    
}
