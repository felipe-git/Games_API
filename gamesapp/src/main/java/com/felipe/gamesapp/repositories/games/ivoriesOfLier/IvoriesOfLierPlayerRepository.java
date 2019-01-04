package com.felipe.gamesapp.repositories.games.ivoriesOfLier;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;

public interface IvoriesOfLierPlayerRepository extends CrudRepository<IvoriesOfLierPlayer, Integer> {

	public List<IvoriesOfLierPlayer> findByIvoriesOfLierGameIdAndPlayingOrderByOrderValue(int ivoriesOfLierGameId, boolean playing);

	public List<IvoriesOfLierPlayer> findByIvoriesOfLierGameId(Integer ivoriesOfLierGameId);
}
