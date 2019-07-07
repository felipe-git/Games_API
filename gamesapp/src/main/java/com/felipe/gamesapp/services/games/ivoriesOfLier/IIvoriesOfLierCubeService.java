package com.felipe.gamesapp.services.games.ivoriesOfLier;

import java.util.List;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierCube;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;

public interface IIvoriesOfLierCubeService {

	public List<IvoriesOfLierCube> createPlayerCubes(IvoriesOfLierPlayer player);

	public List<IvoriesOfLierCube> getPlayerCubes(int ivoriesOfLierGameId, int value);
}
