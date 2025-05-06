package com.yashyadav.newJournalApplication.repository;


import com.yashyadav.newJournalApplication.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByUserName(String userName);

    void deleteByUserName(String name);
}
