package com.felipe.gamesapp.repositories.games.ivoriesOfLier;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierCube;

public interface IvoriesOfLierCubeRepository extends CrudRepository<IvoriesOfLierCube, Integer> {

	public List<IvoriesOfLierCube> findByUserIdAndIvoriesOfLierGameIdAndValue(int userId, int ivoriesOfLierGameId, int value);
}
