package com.felipe.gamesapp.services.games.ivoriesOfLier.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierCube;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGame;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;
import com.felipe.gamesapp.entities.main.User;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierPlayerRepository;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierCubeService;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierPlayerService;

@Service
public class IvoriesOfLierPlayerServiceImpl implements IIvoriesOfLierPlayerService {

	@Autowired
	private IvoriesOfLierPlayerRepository playerRepository;
	@Autowired
	private IIvoriesOfLierCubeService cubeService;

	@Override
	@Transactional
	public IvoriesOfLierPlayer createPlayer(User user, IvoriesOfLierGame game, int order) {
		IvoriesOfLierPlayer player = new IvoriesOfLierPlayer();
		player.setUser(user);
		player.setGame(game);
		player.setOrderValue(order);
		player.setPlaying(true);
		playerRepository.save(player);
		
		List<IvoriesOfLierCube> cubes = cubeService.createPlayerCubes(player);
		player.setCubes(cubes);
		
		return player;
	}

}
