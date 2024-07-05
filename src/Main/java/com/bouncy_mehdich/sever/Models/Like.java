package com.bouncy_mehdich.sever.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Like {

    @JsonProperty("postID")
    String PostID;
    @JsonProperty("likerID")
    String LikerID;


    public Like(String postID, String likerID) {
        PostID = postID;
        LikerID = likerID;

    }

    public String getPostID() {
        return PostID;
    }

    public String getLikerID() {
        return LikerID;
    }



    public void setPostID(String postID) {
        PostID = postID;
    }

    public void setLikerID(String likerID) {
        LikerID = likerID;
    }


}
