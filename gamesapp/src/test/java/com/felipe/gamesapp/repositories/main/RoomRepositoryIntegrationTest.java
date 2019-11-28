package com.felipe.gamesapp.repositories.main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.felipe.gamesapp.entities.main.Room;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private RoomRepository roomRepository;

	@Test
	public void whenFindByNameAndGame_thenReturnRoom() {
		 // given
		Room room = new Room();
		room.setName("felipesRoom");
		room.setGame(1);
	    entityManager.persist(room);
	    entityManager.flush();
	 
	    // when
	    Room found = roomRepository.findByNameAndGame(room.getName(), room.getGame());
	 
	    // then
	    assertThat(found.getName())
	      .isEqualTo(room.getName());

	    assertThat(found.getGame())
	      .isEqualTo(room.getGame());
	}
}
