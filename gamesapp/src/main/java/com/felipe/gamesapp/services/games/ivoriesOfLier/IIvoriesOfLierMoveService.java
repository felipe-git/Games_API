package com.felipe.gamesapp.services.games.ivoriesOfLier;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierMove;

public interface IIvoriesOfLierMoveService {

	public String makeMove(int playerId, int gameId, IvoriesOfLierMove move) throws Exception;
}
