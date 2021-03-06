package com.felipe.gamesapp.services.games.ivoriesOfLier.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierCube;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierCubeRepository;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierCubeService;

@Service
public class IvoriesOfLierCubeServiceImpl implements IIvoriesOfLierCubeService {

	@Autowired
	private transient IvoriesOfLierCubeRepository cubeRepository;


	@Override
	@Transactional
	public List<IvoriesOfLierCube> createPlayerCubes(IvoriesOfLierPlayer player) {
		List<IvoriesOfLierCube> cubes = new ArrayList<IvoriesOfLierCube>(5);
		for(int i = 0; i <= 4; i++) {
			IvoriesOfLierCube cube = new IvoriesOfLierCube();
			cube.setPlayer(player);
			Random random = new Random();
			cube.setValue(random.nextInt(5) + 1);
			cubeRepository.save(cube);
			
			cubes.add(cube);
		}

		return cubes;
	}

	@Override
	public List<IvoriesOfLierCube> getPlayerCubesByValue(List<IvoriesOfLierPlayer> players, int value) {
		List<IvoriesOfLierCube> cubes = new ArrayList<IvoriesOfLierCube>();
		
		for(IvoriesOfLierPlayer player : players) {
			List<IvoriesOfLierCube> playerCubesByValue = player.getCubes().stream()
					.filter(c -> c.getValue().equals(value)).collect(Collectors.toList());
			cubes.addAll(playerCubesByValue);
		}
		
		return cubes;
	}
}
