package com.dao;

import java.util.List;

import com.entity.Guest;

public interface GuestDao {
	
	public void add(Guest guest);

	public void remove(Guest guest);

	public void update(Guest guest);

	public void read(Guest guest);
	
	public List<Guest> readAll();
	
	public List<Guest> readAllinHotel();
	
}
