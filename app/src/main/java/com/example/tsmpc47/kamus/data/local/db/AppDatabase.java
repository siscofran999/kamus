package com.example.tsmpc47.kamus.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.tsmpc47.kamus.data.local.db.dao.EngDao;
import com.example.tsmpc47.kamus.data.local.db.dao.IndoDao;
import com.example.tsmpc47.kamus.data.model.db.Eng;
import com.example.tsmpc47.kamus.data.model.db.Indo;

@Database(entities = {Eng.class, Indo.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EngDao engDao();

    public abstract IndoDao indoDao();

}
