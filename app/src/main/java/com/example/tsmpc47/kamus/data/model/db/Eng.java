package com.example.tsmpc47.kamus.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "english")
public class Eng {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "words")
    public String words;

    @ColumnInfo(name = "translation")
    public String translation;

    public Eng(int id, String words, String translation) {
        this.id = id;
        this.words = words;
        this.translation = translation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
