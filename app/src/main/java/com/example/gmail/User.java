package com.example.gmail;

public class User {
    private String name, lastMessage, lastMsgTime;
    private int imageId;

    public User(String name, String lastMessage, String lastMsgTime, int imageId) {
        this.name        = name;
        this.lastMessage = lastMessage;
        this.lastMsgTime = lastMsgTime;
        this.imageId     = imageId;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getImageId() {
        return imageId;
    }

    public String getLastMsgTime() {
        return lastMsgTime;
    }
}
