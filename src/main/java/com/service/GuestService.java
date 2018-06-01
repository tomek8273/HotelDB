package com.service;

import java.util.ArrayList;
import java.util.List;

import com.entity.Guest;

public interface GuestService {
	public void addGuest(Guest guest);

	public void removeGuest(Guest guest);

	public void updateGuest(Guest guest);

	public void readGuestList(Guest guest);
	
	public List<Guest> readAll();
	
	
}
