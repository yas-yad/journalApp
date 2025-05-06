package com.yashyadav.newJournalApplication.service;

import com.yashyadav.newJournalApplication.entity.JournalEntry;
import com.yashyadav.newJournalApplication.entity.User;
import com.yashyadav.newJournalApplication.repository.JournalEntryRepository;
import com.yashyadav.newJournalApplication.repository.UserRepository;
import com.yashyadav.newJournalApplication.services.UserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTests {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));

    }
}
