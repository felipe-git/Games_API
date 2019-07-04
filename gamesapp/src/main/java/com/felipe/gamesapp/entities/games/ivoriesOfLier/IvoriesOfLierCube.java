package com.felipe.gamesapp.entities.games.ivoriesOfLier;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class IvoriesOfLierCube {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
	private IvoriesOfLierGame game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
	private IvoriesOfLierPlayer player;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

	public IvoriesOfLierGame getGame() {
		return game;
	}
	public void setGame(IvoriesOfLierGame game) {
		this.game = game;
	}

    public IvoriesOfLierPlayer getPlayer() {
        return player;
    }
    public void setPlayer(IvoriesOfLierPlayer player) {
        this.player = player;
    }
}
