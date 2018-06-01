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
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String roomNumber;
	private String number_of_beds;
	
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
		return number_of_beds;
	}
	public void setNumber_of_beds(String string) {
		this.number_of_beds = string;
	}
	
	//@OneToOne (cascade = CascadeType.ALL)
	//private Equipment equpiment;
	
	//@OneToMany (cascade = CascadeType.ALL)
	//private Collection<Room_service> room_service_list = new ArrayList<Room_service>();
	
}
