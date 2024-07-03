package com.bouncy_mehdich.Client.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Like {

    @JsonProperty("postID")
    String PostID;
    @JsonProperty("likerID")
    String LikerID;
    @JsonProperty("likerFirstName")
    String LikerFirstName;
    @JsonProperty("likerLastName")
    String LikerLastName;

    public Like(String postID, String likerID, String likerFirstName, String likerLastName) {
        PostID = postID;
        LikerID = likerID;
        LikerFirstName = likerFirstName;
        LikerLastName = likerLastName;
    }

    public String getPostID() {
        return PostID;
    }

    public String getLikerID() {
        return LikerID;
    }

    public String getLikerFirstName() {
        return LikerFirstName;
    }

    public String getLikerLastName() {
        return LikerLastName;
    }

    public void setPostID(String postID) {
        PostID = postID;
    }

    public void setLikerID(String likerID) {
        LikerID = likerID;
    }

    public void setLikerFirstName(String likerFirstName) {
        LikerFirstName = likerFirstName;
    }

    public void setLikerLastName(String likerLastName) {
        LikerLastName = likerLastName;
    }
}
