package com.felipe.gamesapp.entities.main;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Room {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    private boolean activeGame;
    private Integer ownerId;
    private Integer game;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_room",
			joinColumns = @JoinColumn(name = "roomId"),
			inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> usersInRoom = new HashSet<>();;

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean isActiveGame() {
		return activeGame;
	}
	public void setActiveGame(boolean activeGame) {
		this.activeGame = activeGame;
	}

	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getGame() {
		return game;
	}
	public void setGame(Integer game) {
		this.game = game;
	}

	public Set<User> getUsersInRoom() {
		return usersInRoom;
	}
}
