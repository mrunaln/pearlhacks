package com.springapp.mvc;

/**
 * Created by mrunalnargunde on 3/22/15.
 */
public class Tweet {
    private long id;
    private String text;

    public Tweet(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
