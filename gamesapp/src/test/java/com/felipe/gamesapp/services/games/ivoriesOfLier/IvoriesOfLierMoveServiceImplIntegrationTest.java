package com.felipe.gamesapp.services.games.ivoriesOfLier;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.felipe.gamesapp.services.games.ivoriesOfLier.impl.IvoriesOfLierMoveServiceImpl;

@RunWith(SpringRunner.class)
public class IvoriesOfLierMoveServiceImplIntegrationTest {

	@TestConfiguration
    static class IvoriesOfLierCubeServiceImplTestContextConfiguration {
  
        @Bean
        public IIvoriesOfLierMoveService ivoriesOfLierMoveService() {
            return new IvoriesOfLierMoveServiceImpl();
        }
    }

	//@Autowired
	
}
