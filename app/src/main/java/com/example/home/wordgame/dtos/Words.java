package com.example.home.wordgame.dtos;

/**
 * Created by HOME on 6/18/2017.
 */

public class Words {
    private long id;
    private String word;
    private String meaning;
    private String description;

    public Words(){

    }

    public Words(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
