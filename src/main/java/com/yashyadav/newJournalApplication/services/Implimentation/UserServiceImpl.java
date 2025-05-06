package com.yashyadav.newJournalApplication.services.Implimentation;

import com.yashyadav.newJournalApplication.repository.UserRepository;
import com.yashyadav.newJournalApplication.services.UserService;
import com.yashyadav.newJournalApplication.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private PasswordEncoder passwordEncoder;


    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(new ArrayList<>());
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.info("information ");
            log.error("error ");
            log.warn("warning ");
            log.trace("tracing ");
            log.debug("debugging ");
            return false;
        }
    }
    @Override
    public User saveUser(User user) {
        return  userRepository.save(user);
    }

    @Override
    public User add(String name, String pass) {
        return null;
    }

    @Override
    public void deleteUser(String name) {
        userRepository.deleteByUserName(name);
    }
}
