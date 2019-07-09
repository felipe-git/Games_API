package com.felipe.gamesapp.repositories.games.ivoriesOfLier;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;

public interface IvoriesOfLierPlayerRepository extends CrudRepository<IvoriesOfLierPlayer, Integer> {

	public List<IvoriesOfLierPlayer> findByGame_IdAndPlayingOrderByOrderValue(int gameId, boolean playing);

	public List<IvoriesOfLierPlayer> findByGame_Id(Integer gameId);
}
