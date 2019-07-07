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
import javax.persistence.OneToOne;

import com.felipe.gamesapp.entities.main.User;

@Entity
public class IvoriesOfLierPlayer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
	private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
	private IvoriesOfLierGame game;

	@OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
	private List<IvoriesOfLierCube> cubes = new ArrayList<>();

	private boolean playing;
	private Integer orderValue;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public IvoriesOfLierGame getGame() {
		return game;
	}
	public void setGame(IvoriesOfLierGame game) {
		this.game = game;
	}

	public boolean isPlaying() {
		return playing;
	}
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public Integer getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public List<IvoriesOfLierCube> getCubes() {
		return cubes;
	}
	public void setCubes(List<IvoriesOfLierCube> cubes) {
		this.cubes = cubes;
	}
}
