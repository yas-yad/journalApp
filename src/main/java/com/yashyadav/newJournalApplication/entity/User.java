package com.yashyadav.newJournalApplication.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique=true)
    private String userName;
    @NonNull
    private String password;
    @Getter
    @Setter
    @DBRef
    public List<JournalEntry> journalEntryList=new ArrayList<>();
    @Getter
    @Setter
    private List<String> roles;


    public @NonNull String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public @NonNull String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

}
