package com.felipe.gamesapp.repositories.main;

import org.springframework.data.repository.CrudRepository;

import com.felipe.gamesapp.entities.main.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {

	public Room findByNameAndGameId(String name, int gameId);
}
