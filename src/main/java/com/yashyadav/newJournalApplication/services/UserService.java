package com.yashyadav.newJournalApplication.services;



import com.yashyadav.newJournalApplication.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User findByUserName(String userName);

    boolean saveNewUser(User user);

    User saveUser(User user);

    User add(String name, String pass);

    void deleteUser(String name);
}
