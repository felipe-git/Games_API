package com.felipe.gamesapp.services.games.ivoriesOfLier;

import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierCube;
import com.felipe.gamesapp.entities.games.ivoriesOfLier.IvoriesOfLierPlayer;
import com.felipe.gamesapp.repositories.games.ivoriesOfLier.IvoriesOfLierCubeRepository;
import com.felipe.gamesapp.services.games.ivoriesOfLier.impl.IvoriesOfLierCubeServiceImpl;

@RunWith(SpringRunner.class)
public class IvoriesOfLierCubeServiceImplIntegrationTest {

	@TestConfiguration
    static class IvoriesOfLierCubeServiceImplTestContextConfiguration {
  
        @Bean
        public IIvoriesOfLierCubeService ivoriesOfLierCubeService() {
            return new IvoriesOfLierCubeServiceImpl();
        }
    }
 
    @Autowired
    private IIvoriesOfLierCubeService ivoriesOfLierCubeService;
 
    @MockBean
    private IvoriesOfLierCubeRepository ivoriesOfLierCubeRepository;
  
    
   
    @Before
    public void setUp() {
//    	IvoriesOfLierPlayer player = new IvoriesOfLierPlayer();
//    	player.se
//    	IvoriesOfLierCube cube = new IvoriesOfLierCube();
//		cube.setGamerId(4);
//		cube.setIvoriesOfLierGameId(1);
//		cube.setValue(6);
//		
//		List<IvoriesOfLierCube> found = ivoriesOfLierCubeRepository
//	    		.findByIvoriesOfLierGameIdAndValue(cube.getIvoriesOfLierGameId(), cube.getValue());
//
//        Mockito.when(found)
//          .thenReturn(found);
    }
}
