package com.yashyadav.newJournalApplication.services.Implimentation;

import com.yashyadav.newJournalApplication.entity.JournalEntry;
import com.yashyadav.newJournalApplication.entity.User;
import com.yashyadav.newJournalApplication.repository.JournalEntryRepository;
import com.yashyadav.newJournalApplication.services.JournalEntryServices;
import com.yashyadav.newJournalApplication.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryServicesImpl implements JournalEntryServices {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user=userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry save = journalEntryRepository.save(journalEntry);
            user.getJournalEntryList().add(save);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry ",e);
        }
    }

    @Override
    public Optional<JournalEntry> findById(ObjectId myId) {
        return journalEntryRepository.findById(myId);
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getOne(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteEntry(ObjectId id, String userName) {
        boolean removed=false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry ",e);
        }
        return removed;
    }

//    public List<JournalEntry> findByUserName(String UserName){
//
//    }
}
