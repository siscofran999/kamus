package com.example.tsmpc47.kamus.ui.main;

import com.example.tsmpc47.kamus.data.model.Word;

import java.util.List;

public interface MainNavigator {
    void onSearchClicked();

    void wipeText();

    void subscribeToLiveData();

    void setUp();
}
