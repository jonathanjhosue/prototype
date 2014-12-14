package com.company.prototype.model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String accountNumber;

	private List <Card> cards= new ArrayList<Card>() ;
	
	private List<StateAccount> stateAccounts= new ArrayList<StateAccount>();

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<StateAccount> getStateAccounts() {
		return stateAccounts;
	}

	public void setStateAccounts(List<StateAccount> stateAccounts) {
		this.stateAccounts = stateAccounts;
	}
	
	

}
