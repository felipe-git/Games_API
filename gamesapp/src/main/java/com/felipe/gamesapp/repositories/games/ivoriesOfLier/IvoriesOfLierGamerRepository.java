package com.felipe.gamesapp.repositories.games.ivoriesOfLier;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGamer;

public interface IvoriesOfLierGamerRepository extends CrudRepository<IvoriesOfLierGamer, Integer> {

	public List<IvoriesOfLierGamer> findByIvoriesOfLierGameIdAndPlayingOrderByOrder(int ivoriesOfLierGameId, boolean playing);

	public List<IvoriesOfLierGamer> findByIvoriesOfLierGameId(Integer ivoriesOfLierGameId);
}
