package com.felipe.gamesapp.repositories.games.ivoriesOfLier;

import org.springframework.data.repository.CrudRepository;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierMove;

public interface IvoriesOfLierMoveRepository extends CrudRepository<IvoriesOfLierMove, Integer> {
	
	public IvoriesOfLierMove findByPlayerIdAndGameIdOrderByIdDesc(int playerId, int gameId);
}
