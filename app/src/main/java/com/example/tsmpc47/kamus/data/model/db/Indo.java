package com.example.tsmpc47.kamus.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "indonesia")
public class Indo {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "words")
    public String words;

    @ColumnInfo(name = "translation")
    public String translation;


}
