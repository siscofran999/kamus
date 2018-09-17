package com.example.tsmpc47.kamus.data.model;

public class Words {

    int id;
    String words;
    String translation;

    public Words(int id, String words, String translation) {
        this.id = id;
        this.words = words;
        this.translation = translation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
