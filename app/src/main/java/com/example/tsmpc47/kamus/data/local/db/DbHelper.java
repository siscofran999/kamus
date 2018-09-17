package com.example.tsmpc47.kamus.data.local.db;

import com.example.tsmpc47.kamus.data.model.Words;
import com.example.tsmpc47.kamus.data.model.db.Eng;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    Observable<List<Eng>> fetchDatabaseEngInd();

}
