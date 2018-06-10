package com.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Guest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String guestFirstName;
	private String guestLastName;
	private String guestDateOfBirth;
	private String guestPesel;

	@OneToMany (cascade = CascadeType.ALL)
	private Collection<Invoice> invoice_list = new ArrayList<Invoice>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return guestFirstName;
	}

	public void setFirst_name(String first_name) {
		this.guestFirstName = first_name;
	}

	public String getLast_name() {
		return guestLastName;
	}

	public void setLast_name(String last_mame) {
		this.guestLastName = last_mame;
	}

	public String getDate_of_birth() {
		return guestDateOfBirth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.guestDateOfBirth = date_of_birth;
	}

	public String getPesel() {
		return guestPesel;
	}

	public void setPesel(String pesel) {
		guestPesel = pesel;
	}


	public Collection<Invoice> getInvoice_list() {
		return invoice_list;
	}

	public void setInvoice_list(Collection<Invoice> invoice_list) {
		this.invoice_list = invoice_list;
	} 
}
