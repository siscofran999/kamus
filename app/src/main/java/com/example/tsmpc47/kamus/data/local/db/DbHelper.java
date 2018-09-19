package com.example.tsmpc47.kamus.data.local.db;

import com.example.tsmpc47.kamus.data.model.Word;

import java.sql.SQLException;
import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    Observable<List<Word>> fetchDatabaseEngInd();

    AppDbHelper openDB() throws SQLException;

}
