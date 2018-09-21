package com.example.tsmpc47.kamus.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.tsmpc47.kamus.data.DataManager;
import com.example.tsmpc47.kamus.data.model.Word;
import com.example.tsmpc47.kamus.ui.base.BaseViewModel;
import com.example.tsmpc47.kamus.utils.rx.SchedulerProvider;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.KamusColumnsEngInd.SEARCH_WORD_ENG_IND;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.KamusColumnsIndEng.SEARCH_WORD_IND_ENG;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.TABLE_NAME_ENG_IND;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.TABLE_NAME_IND_ENG;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private static final String TAG = "MainViewModel";
    private Boolean typeWord;
    public ObservableField<String> searchObs = new ObservableField<>("English");
    public ObservableField<String> resultObs = new ObservableField<>("Indonesian");
    public ObservableInt showVisibility;
    public ObservableField<String> resultText = new ObservableField<>("");
    private final ObservableArrayList<Word> kamusObservableArrayList = new ObservableArrayList<>();
    private final MutableLiveData<List<Word>> kamusListLiveData;

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        kamusListLiveData = new MutableLiveData<>();
        showVisibility = new ObservableInt();
        typeWord = false;
    }

    public void setEdt(EditText edtSearch) {
        getCompositeDisposable().add(
                RxTextView.textChanges(edtSearch)
                .debounce(1, TimeUnit.SECONDS)
                .filter(charSequence -> charSequence.toString().trim().length() > 1)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(charSequence ->
                        setTypeWord(charSequence.toString()),
                        throwable -> Log.e(TAG, "accept: "+throwable.getMessage()))
        );
    }

    public void switchLanguage() {
        getNavigator().wipeText();
        typeWord =! typeWord;
        if (typeWord == true) {
            searchObs.set("Indonesian");
            resultObs.set("English");

            Log.i(TAG, "switchLanguage: ENG");

        } else if (typeWord == false) {
            searchObs.set("English");
            resultObs.set("Indonesian");

            Log.i(TAG, "switchLanguage: IND");
        }
    }

    public void setSingleSearch(String word) {
        if (searchObs.get().equals("English")) {
            Log.i(TAG, "setSingleSearch: Eng : "+word);
            setSingleSearchEngInd(word);

        } else if (searchObs.get().equals("Indonesian")) {
            setSingleSearchIndEng(word);
            Log.i(TAG, "setSingleSearch: IND : "+word);
        }
    }

    public void setTypeWord(String word) {
        Log.i(TAG, "setTypeWord: "+word);
        if (searchObs.get().equals("English")) {
            Log.i(TAG, "setTypeWord: ENG");
            showVisibility.set(View.VISIBLE);

            setSearchEngInd(word);

        } else if (searchObs.get().equals("Indonesian")) {
            Log.i(TAG, "setTypeWord: Indonesian");
            showVisibility.set(View.INVISIBLE);

            setSearchIndEng(word);
        }
    }

    private void setSearchIndEng(String word) {
        getDataManager().openDB();
        getCompositeDisposable().add(getDataManager().getBySearchWord(word,TABLE_NAME_IND_ENG, SEARCH_WORD_IND_ENG)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(words -> {
                    kamusListLiveData.setValue(words);
                }, throwable ->
                        Log.e(TAG, "accept: "+throwable.getMessage())));
        getDataManager().closeDb();
    }

    private void setSearchEngInd(String word) {
        getDataManager().openDB();
        getCompositeDisposable().add(getDataManager().getBySearchWord(word,TABLE_NAME_ENG_IND, SEARCH_WORD_ENG_IND)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(words -> {
                    kamusListLiveData.setValue(words);
                }, throwable ->
                        Log.e(TAG, "accept: "+throwable.getMessage())));
        getDataManager().closeDb();
    }

    private void setSingleSearchIndEng(String word) {
        getDataManager().openDB();
        getCompositeDisposable().add(getDataManager().getBySearchWord(word,TABLE_NAME_IND_ENG, SEARCH_WORD_IND_ENG)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(words -> {
                    String search = words.get(0).getWords();
                    String result = words.get(0).getTranslation();

                    resultText.set(search + " : \n" + result);
                }, throwable ->
                        Log.e(TAG, "accept: "+throwable.getMessage())));
        getDataManager().closeDb();
    }

    private void setSingleSearchEngInd(String word) {
        getDataManager().openDB();
        getCompositeDisposable().add(getDataManager().getBySearchWord(word,TABLE_NAME_ENG_IND, SEARCH_WORD_ENG_IND)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(words -> {
                    String search = words.get(0).getWords();
                    String result = words.get(0).getTranslation();

                    Log.i(TAG, "setSingleSearchEngInd: "+words.get(0).getTranslation());

                    resultText.set(search + " : \n" + result);
                }, throwable ->
                        Log.e(TAG, "accept: "+throwable.getMessage())));
        getDataManager().closeDb();
    }

    public void onSearchClicked() {
        Log.i(TAG, "onSearchClicked: click btn");
        getNavigator().onSearchClicked();
    }

    public MutableLiveData<List<Word>> getKamusListLiveData() {
        return kamusListLiveData;
    }

    public ObservableArrayList<Word> getKamusObservableArrayList() {
        return kamusObservableArrayList;
    }

    public void addlistItemsToList(List<Word> words) {
        kamusObservableArrayList.clear();
        kamusObservableArrayList.addAll(words);
    }
}
