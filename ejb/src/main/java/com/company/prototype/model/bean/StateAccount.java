package com.company.prototype.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StateAccount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String accountNumber;
	
	Date date;
	
	BigDecimal currentBalance;	
	
	BigDecimal lastBalance;
	
	String state;
	
	List<Movement> movements = new ArrayList<Movement>();
	

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public BigDecimal getLastBalance() {
		return lastBalance;
	}

	public void setLastBalance(BigDecimal lastBalance) {
		this.lastBalance = lastBalance;
	}

	public String getState() {
		return state;
	}

	public void setState(String status) {
		this.state = status;
	}
	
		

}
