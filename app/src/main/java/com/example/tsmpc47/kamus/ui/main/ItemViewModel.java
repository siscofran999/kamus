package com.example.tsmpc47.kamus.ui.main;

import android.databinding.ObservableField;

import com.example.tsmpc47.kamus.data.model.Word;

public class ItemViewModel {

    private Word mWord;
    public ObservableField<String> searchText = new ObservableField<>("");
    private int mPost;

    public ItemViewModel(int position, Word word) {
        this.mPost = position;
        this.mWord = word;

        searchText.set(mWord.getWords());
    }
}
