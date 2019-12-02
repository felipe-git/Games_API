package com.felipe.gamesapp.repositories.main;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.felipe.gamesapp.entities.main.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserIvoriesRepository userRepository;

	@Test
	public void whenFindByName_thenReturnUser() {
	    // given
		User felipe = new User();
		felipe.setName("felipe");
	    entityManager.persist(felipe);
	    entityManager.flush();
	 
	    // when
	    User found = userRepository.findByName(felipe.getName());
	 
	    // then
	    assertThat(found.getName())
	      .isEqualTo(felipe.getName());
	}
}
