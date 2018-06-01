package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.RoomDao;
import com.entity.Room;

public class RoomServiceImpl {
	
	@Autowired
	RoomDao DaoRoom;
	
	public void addRoom(Room room) {
		DaoRoom.addRoom(room);
	}
	
	
}
