package com.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.dao.GuestDao;
import com.entity.Guest;
import com.service.GuestService;

public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDao guestDao;

	public void addGuest(Guest guest) {
		guestDao.add(guest);
	}

	public void removeGuest(Guest guest) {
		guestDao.remove(guest);
	}

	public GuestDao getDaoGuest() {
		return guestDao;
	}

	public void setDaoGuest(GuestDao daoGuest) {
		guestDao = daoGuest;
	}

	public void updateGuest(Guest guest) {
		// TODO Auto-generated method 

	}

	public void readGuestList(Guest guest) {
		guestDao.read(guest);
	}

	public List<Guest> readAll() {
		System.out.println("jestem w metodzie readAll w GuestService");
		return guestDao.readAll();
	}
	
	public List<Guest> readAllinHotel(){
		return guestDao.readAllinHotel();
	}

}
