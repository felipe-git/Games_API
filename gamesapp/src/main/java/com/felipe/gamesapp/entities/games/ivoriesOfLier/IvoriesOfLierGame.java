package com.felipe.gamesapp.entities.games.ivoriesOfLier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.felipe.gamesapp.entities.main.Room;

@Entity
public class IvoriesOfLierGame {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
	private Room room;

	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
	private List<IvoriesOfLierPlayer> players = new ArrayList<>();

	private Integer playerIdNextMove;
	private Integer playerIdPreviousMove;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}

	public List<IvoriesOfLierPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<IvoriesOfLierPlayer> players) {
		this.players = players;
	}

	public Integer getPlayerIdNextMove() {
		return playerIdNextMove;
	}
	public void setPlayerIdNextMove(Integer playerIdNextMove) {
		this.playerIdNextMove = playerIdNextMove;
	}

	public Integer getPlayerIdPreviousMove() {
		return playerIdPreviousMove;
	}
	public void setPlayerIdPreviousMove(Integer playerIdPreviousMove) {
		this.playerIdPreviousMove = playerIdPreviousMove;
	}
}
