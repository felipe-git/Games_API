package com.felipe.gamesapp.services.main;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.felipe.gamesapp.entities.main.User;
import com.felipe.gamesapp.repositories.main.RoomRepository;
import com.felipe.gamesapp.repositories.main.UserRepository;
import com.felipe.gamesapp.services.main.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceImplIntegrationTest {

	@TestConfiguration
    static class UserServiceImplIntegrationTestContextConfiguration {

        @Bean
        public IUserService userServiceImpl() {
        	return new UserServiceImpl();
        }
	}

	@Autowired
	private IUserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoomRepository roomRepository;


    @Before
    public void setUp() {
//    	User zenek = new User();
//    	zenek.setName("zenek");
//  
//    	Mockito.when(userRepository.findByName(zenek.getName()))
//        	.thenReturn(zenek);
    }
   
    @Test
    public void testCreateUser() {
    	User zenek = new User();
    	zenek.setName("zenek");
    	
//    	Mockito.when(userRepository.findByName(zenek.getName()))
//    		.thenReturn(zenek);
//    	Mockito.when(userRepository.save(zenek))
//    		.thenReturn(zenek);
    	
    	Mockito.when(userRepository.save(any(User.class)))
			.thenAnswer(new Answer<User>() {
				@Override
				public User answer(InvocationOnMock invocation) throws Throwable {
					User zenek = new User();
			    	zenek.setName("zenek");
					return zenek;
				}
			});
    	
    	User found = userService.createUser(zenek.getName());
    	
    	assertNotNull(found);
    }
}
