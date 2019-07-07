package com.felipe.gamesapp.services.main;

import com.felipe.gamesapp.constants.Games;
import com.felipe.gamesapp.entities.main.Room;

public interface IRoomService {

	public Room createRoomOrGetIfExist(String name, int ownerId, Games game);
	
	public Room getRoom(int roomId) throws Exception;
}
