package com.yashyadav.newJournalApplication.service;

import com.yashyadav.newJournalApplication.entity.User;
import com.yashyadav.newJournalApplication.repository.UserRepository;
import com.yashyadav.newJournalApplication.services.Implimentation.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadUserByUsername(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("yash").password("yash").roles(new ArrayList<>()).build());
        UserDetails userDetails=userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(userDetails);
    }

}
