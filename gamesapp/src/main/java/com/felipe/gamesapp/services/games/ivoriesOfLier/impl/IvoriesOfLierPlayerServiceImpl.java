package com.felipe.gamesapp.services.games.ivoriesOfLier.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGame;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;
import com.felipe.gamesapp.entities.main.User;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierPlayerRepository;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierPlayerService;

public class IvoriesOfLierPlayerServiceImpl implements IIvoriesOfLierPlayerService {

	@Autowired
	private IvoriesOfLierPlayerRepository gamerRepository;

	@Override
	@Transactional
	public IvoriesOfLierPlayer createPlayer(User user, IvoriesOfLierGame game, int order) {
		IvoriesOfLierPlayer gamer = new IvoriesOfLierPlayer();
		gamer.setUser(user);
		gamer.setGame(game);
		gamer.setOrderValue(order);
		gamer.setPlaying(true);
		gamerRepository.save(gamer);
		
		return gamer;
	}

}
