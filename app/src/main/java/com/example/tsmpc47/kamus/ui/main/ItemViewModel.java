package com.example.tsmpc47.kamus.ui.main;

import android.databinding.ObservableField;
import android.util.Log;

import com.example.tsmpc47.kamus.data.model.Word;

public class ItemViewModel {

    private Word mWord;
    public ObservableField<String> searchText = new ObservableField<>("");
    private int mPost;
    private static final String TAG = "ItemViewModel";
    public ItemResultViewModelListener mListener;

    public ItemViewModel(int position, Word word, ItemResultViewModelListener listener) {
        this.mPost = position;
        this.mWord = word;
        this.mListener = listener;

        searchText.set(mWord.getWords());
    }

    public void onItemClicked(){
        mListener.onItemClick(mPost);
    }

    public interface ItemResultViewModelListener {
        void onItemClick(int post);
    }
}
