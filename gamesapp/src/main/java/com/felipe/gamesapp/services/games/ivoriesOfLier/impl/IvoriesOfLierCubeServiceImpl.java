package com.felipe.gamesapp.services.games.ivoriesOfLier.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierCube;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierCubeRepository;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IvoriesOfLierCubeService;

public class IvoriesOfLierCubeServiceImpl implements IvoriesOfLierCubeService {

	@Autowired
	private transient IvoriesOfLierCubeRepository cubeRepository;

	@Override
	public List<IvoriesOfLierCube> getPlayerCubes(int ivoriesOfLierGameId, int value) {
		return cubeRepository.findByIvoriesOfLierGameIdAndValue(ivoriesOfLierGameId, value);
	}
}
