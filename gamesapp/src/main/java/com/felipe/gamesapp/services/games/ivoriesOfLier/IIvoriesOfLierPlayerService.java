package com.felipe.gamesapp.services.games.ivoriesOfLier;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGame;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;
import com.felipe.gamesapp.entities.main.User;

public interface IIvoriesOfLierPlayerService {

	public IvoriesOfLierPlayer createPlayer(User user, IvoriesOfLierGame game, int order);
}
