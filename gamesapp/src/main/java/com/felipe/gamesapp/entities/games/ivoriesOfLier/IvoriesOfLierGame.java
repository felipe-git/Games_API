package com.felipe.gamesapp.entities.games.ivoriesOfLier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IvoriesOfLierGame {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer roomId;
	private Integer userIdLastMoved;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getUserIdLastMoved() {
		return userIdLastMoved;
	}

	public void setUserIdLastMoved(Integer userIdLastMoved) {
		this.userIdLastMoved = userIdLastMoved;
	}
}
