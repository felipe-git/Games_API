package com.felipe.gamesapp.services.games.ivoriesOfLier.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGame;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierMove;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierGameRepository;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierGameService;
import com.felipe.gamesapp.services.games.ivoriesOfLier.IIvoriesOfLierMoveService;

public class IvoriesOfLierMoveServiceImpl implements IIvoriesOfLierMoveService {

	@Autowired
	private IvoriesOfLierGameRepository gameRepository;
	@Autowired
	private IIvoriesOfLierGameService gameService;
	
	@Override
	@Transactional
	public void makeMove(int playerId, int gameId, IvoriesOfLierMove move) {
		IvoriesOfLierGame game = gameRepository.findById(gameId).get();
		
		List<IvoriesOfLierPlayer> players = game.getPlayers();
		
		// TODO sprawdzenie czy ruch jest możliwy
		
		
		// bieżący gracz
		IvoriesOfLierPlayer currentPlayer = players.stream().filter(g -> g.getId().equals(playerId)).findFirst().orElse(null);
		
		
	}
}
