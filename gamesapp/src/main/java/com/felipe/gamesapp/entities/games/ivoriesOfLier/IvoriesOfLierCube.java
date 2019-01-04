package com.felipe.gamesapp.entities.games.ivoriesOfLier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IvoriesOfLierCube {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer gamerId;
	private Integer ivoriesOfLierGameId;
	private Integer value;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGamerId() {
		return gamerId;
	}
	public void setGamerId(Integer gamerId) {
		this.gamerId = gamerId;
	}
	public Integer getIvoriesOfLierGameId() {
		return ivoriesOfLierGameId;
	}
	public void setIvoriesOfLierGameId(Integer ivoriesOfLierGameId) {
		this.ivoriesOfLierGameId = ivoriesOfLierGameId;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
}
