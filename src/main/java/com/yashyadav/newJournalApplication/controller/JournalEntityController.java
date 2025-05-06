package com.yashyadav.newJournalApplication.controller;

import com.yashyadav.newJournalApplication.entity.JournalEntry;
import com.yashyadav.newJournalApplication.entity.User;
import com.yashyadav.newJournalApplication.services.JournalEntryServices;
import com.yashyadav.newJournalApplication.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntityController {
    @Autowired private JournalEntryServices journalEntryServices;

    @Autowired private UserService userService;






    @GetMapping("/getAll")
    public List<JournalEntry> getAll(){
        return journalEntryServices.getAll();
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntryList();
        if(all!=null && !all.isEmpty())return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            journalEntryServices.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getJournalEntry(@PathVariable ObjectId myId){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x->x.getId().equals(myId)).toList();
        if(!collect.isEmpty()) {
            Optional<JournalEntry> entry = journalEntryServices.findById(myId);
            if (entry.isPresent()) return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        boolean removed=journalEntryServices.deleteEntry(id,userName);
        return removed?new ResponseEntity<>(HttpStatus.NO_CONTENT):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntry(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry myEntry){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x->x.getId().equals(id)).toList();
        if(!collect.isEmpty()) {
            Optional<JournalEntry> entry = journalEntryServices.findById(id);
            if (entry.isPresent()){
                JournalEntry old=entry.get();
                old.setTitle(myEntry.getTitle()!=null && !myEntry.getTitle().equals("")?myEntry.getTitle():old.getTitle());
                old.setContent(myEntry.getContent()!=null && !myEntry.getContent().equals("")?myEntry.getContent(): old.getContent());
                old.setDate(LocalDateTime.now());
                journalEntryServices.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
