package com.elvina.pinknote;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String dateAdded;
    private int priority;

    public Note(String title, String description, String dateAdded, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dateAdded = dateAdded;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getDateAdded() {
        return dateAdded;
    }
}
