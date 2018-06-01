package com.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Guest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String first_name;
	private String last_name;
	private String date_of_birth;
	private String Pesel;

	@OneToMany (cascade = CascadeType.ALL)
	private Collection<Invoice> invoice_list = new ArrayList<Invoice>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Room room;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_mame) {
		this.last_name = last_mame;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getPesel() {
		return Pesel;
	}

	public void setPesel(String pesel) {
		Pesel = pesel;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Collection<Invoice> getInvoice_list() {
		return invoice_list;
	}

	public void setInvoice_list(Collection<Invoice> invoice_list) {
		this.invoice_list = invoice_list;
	} 
	
}
