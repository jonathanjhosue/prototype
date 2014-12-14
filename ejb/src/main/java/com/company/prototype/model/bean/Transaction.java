/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.prototype.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;



/**
 *
 * @author jonathan
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String identification;
    private String reference;
    private Date date;
    private String state;
    private BigDecimal value;
    private String merchandId;
    
    private String fromAccount;
	private String fromEntity;
    
    private String toAccount;    
    private String toEntity;
    
    private String currencyCode;
    
    private String cardNumber;
    
    //private String currencyExchange;
    

    //@XmlAttribute(required = true)
    private String transactionType;

    public Transaction() {
    }

    public Transaction(Long id) {
        this.id = id;
    }

    public Transaction(Long id, String referencia, Date fecha, String estado, BigDecimal valor) {
        this.id = id;
        this.reference = referencia;
        this.date = fecha;
        this.state = estado;
        this.value = valor;
    }

    public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMerchandId() {
		return merchandId;
	}

	public void setMerchandId(String merchandId) {
		this.merchandId = merchandId;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getFromEntity() {
		return fromEntity;
	}

	public void setFromEntity(String fromEntity) {
		this.fromEntity = fromEntity;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getToEntity() {
		return toEntity;
	}

	public void setToEntity(String toEntity) {
		this.toEntity = toEntity;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

    @Override
    public String toString() {
        return "com.company.prototype.model.bean.Transaccion[ id=" + id + " ]";
    }
    
}
