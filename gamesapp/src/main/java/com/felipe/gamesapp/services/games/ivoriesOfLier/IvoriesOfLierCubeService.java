package com.felipe.gamesapp.services.games.ivoriesOfLier;

import java.util.List;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierCube;

public interface IvoriesOfLierCubeService {

	public List<IvoriesOfLierCube> getPlayerCubes(int ivoriesOfLierGameId, int value);
}
