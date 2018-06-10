package com.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.dao.GuestDao;
import com.entity.Guest;
import com.service.GuestService;

public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDao DaoGuest;

	public void addGuest(Guest guest) {
		DaoGuest.add(guest);
	}

	public void removeGuest(Guest guest) {
		DaoGuest.remove(guest);
	}

	public GuestDao getDaoGuest() {
		return DaoGuest;
	}

	public void setDaoGuest(GuestDao daoGuest) {
		DaoGuest = daoGuest;
	}

	public void updateGuest(Guest guest) {
		// TODO Auto-generated method 

	}

	public void readGuestList(Guest guest) {
		DaoGuest.read(guest);
	}

	public List<Guest> readAll() {
		System.out.println("jestem w metodzie readAll w GuestService");
		return DaoGuest.readAll();
	}
	
	public List<Guest> readAllinHotel(){
		return DaoGuest.readAllinHotel();
	}

}
