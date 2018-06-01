package com.dao;

import java.util.List;

import com.entity.Guest;
import com.entity.Room;

public interface RoomDao {
	public void addRoom(Room room);
	
	public void addEquipment();

	public void remove(Room room);

	public void update(Room room);

	public void read(Room room);
	
	public List<Room> readAll();
}
