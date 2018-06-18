package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String roomNumber;
	private String roomNumberOfBeds;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Guest> guests = new ArrayList<Guest>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Equipment> equipmentInRoom = new ArrayList<Equipment>();
	
	public List<Guest> getGuests() {
		return guests;
	}
	
	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String string) {
		this.roomNumber = string;
	}
	public String getNumber_of_beds() {
		return roomNumberOfBeds;
	}
	public void setNumber_of_beds(String string) {
		this.roomNumberOfBeds = string;
	}
	
	
}
