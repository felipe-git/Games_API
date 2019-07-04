package com.felipe.gamesapp.repositories.games.ivoriesOfLier;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierCube;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierGame;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IvoriesOfLierCubeRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private IvoriesOfLierCubeRepository ivoriesOfLierCubeRepository;

	private IvoriesOfLierGame ivoriesOfLierGame;
	private IvoriesOfLierPlayer ivoriesOfLierPlayer;

	@Before
	public void setUp() {
		ivoriesOfLierGame = new IvoriesOfLierGame();
		ivoriesOfLierGame.setRoomId(1);
		ivoriesOfLierGame.setUserIdCurrentMove(1);
		ivoriesOfLierGame.setUserIdPreviousMove(null);
		entityManager.persist(ivoriesOfLierGame);
	    entityManager.flush();
	    
	    ivoriesOfLierPlayer = new IvoriesOfLierPlayer();
	    ivoriesOfLierPlayer.setIvoriesOfLierGameId(ivoriesOfLierGame.getId());
	    entityManager.persist(ivoriesOfLierPlayer);
	    entityManager.flush();
	}

	@Test
	public void whenFindByGameAndValue_thenReturnCubes() {
	    // given
		IvoriesOfLierCube cube = new IvoriesOfLierCube();
		cube.setGamerId(ivoriesOfLierGame.getId());
		cube.setIvoriesOfLierGameId(ivoriesOfLierPlayer.getId());
		cube.setValue(6);
	    entityManager.persist(cube);
	    entityManager.flush();
	 
	    // when
	    List<IvoriesOfLierCube> found = ivoriesOfLierCubeRepository
	    		.findByIvoriesOfLierGameIdAndValue(cube.getIvoriesOfLierGameId(), cube.getValue());
	 
	    // then
	    assertThat(found.size())
	      .isEqualTo(1);
	    
	    IvoriesOfLierCube firstCube = found.get(0);
	    
	    assertThat(firstCube.getIvoriesOfLierGameId())
	      .isEqualTo(cube.getIvoriesOfLierGameId());

	    assertThat(firstCube.getValue())
	      .isEqualTo(cube.getValue());
	}
}
