package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String invoiceNumber;
	private String invoiceValue;
	private String invoicePaid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	Guest guest;
	
	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public String getInvoicePaid() {
		return invoicePaid;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public String getInvoiceValue() {
		return invoiceValue;
	}
	
	public void setInvoiceValue(String string) {
		this.invoiceValue = string;
	}
	
	public String isInvoicePaid() {
		return invoicePaid;
	}
	
	public void setInvoicePaid(String invoicePaid) {
		this.invoicePaid = invoicePaid;
	}
	
}
