package com.example.retrofit1;

import com.google.gson.annotations.SerializedName;

public class Post {
    private  int userId;

    private  int id; //always put the int as nullable (Integer) though in this api no problem

    private String title;

    @SerializedName("body")
    private String text;

    /**
     * Creating our constructor to hold the data that will be sent
     * to the server with using the post method
     * @return
     */
    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    /**
     * Creating the getter methods to hold data that is received
     * from the server using the get request
     * @return
     */
    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
