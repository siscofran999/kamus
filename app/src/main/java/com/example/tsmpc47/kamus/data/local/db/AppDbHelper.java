package com.example.tsmpc47.kamus.data.local.db;

import android.content.Context;
import android.content.res.Resources;

import com.example.tsmpc47.kamus.R;
import com.example.tsmpc47.kamus.data.model.Words;
import com.example.tsmpc47.kamus.data.model.db.Eng;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

@Singleton
public class AppDbHelper implements DbHelper {

    private AppDatabase mAppDatabase;
    private final Context mContext;

    @Inject
    public AppDbHelper(Context context, AppDatabase appDatabase){
        this.mContext = context;
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<List<Eng>> fetchDatabaseEngInd() {
        return Observable.create(emitter -> {
            List<Eng> wordList = preLoadRaw();
            for (Eng word: wordList) {
                mAppDatabase.engDao().insert(word);
            }
        });
    }

    private List<Eng> preLoadRaw() {
        ArrayList<Eng> words = new ArrayList<>();
        String line;
        BufferedReader reader;
        int count = 0;
        Eng word;
        try {
            Resources res = mContext.getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);
            reader = new BufferedReader(new InputStreamReader(raw_dict));
            do {
                line = reader.readLine();
                String[] splitStr = line.split("\t");

                word = new Eng(count,splitStr[0],splitStr[1]);
                words.add(word);
                count++;
            }while (line != null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return words;
    }
}
