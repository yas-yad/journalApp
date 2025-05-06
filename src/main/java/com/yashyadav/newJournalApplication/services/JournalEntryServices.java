package com.yashyadav.newJournalApplication.services;

import com.yashyadav.newJournalApplication.entity.JournalEntry;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface JournalEntryServices {

    void saveEntry(JournalEntry myEntry, String userName);

    Optional<JournalEntry> findById(ObjectId myId);

    boolean deleteEntry(ObjectId id, String userName);

    List<JournalEntry> getAll();

    void saveEntry(JournalEntry old);
}
