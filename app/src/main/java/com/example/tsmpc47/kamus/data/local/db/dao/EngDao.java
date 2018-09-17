package com.example.tsmpc47.kamus.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.tsmpc47.kamus.data.model.Words;
import com.example.tsmpc47.kamus.data.model.db.Eng;

import java.util.List;

@Dao
public interface EngDao {

    @Query("SELECT * FROM english")
    List<Eng> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Eng english);

}