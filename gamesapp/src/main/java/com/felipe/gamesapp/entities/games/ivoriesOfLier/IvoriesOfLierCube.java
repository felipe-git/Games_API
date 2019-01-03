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

	private Integer userId;
	private Integer ivoriesOfLierGameId;
	private Integer number;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getIvoriesOfLierGameId() {
		return ivoriesOfLierGameId;
	}
	public void setIvoriesOfLierGameId(Integer ivoriesOfLierGameId) {
		this.ivoriesOfLierGameId = ivoriesOfLierGameId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
}
