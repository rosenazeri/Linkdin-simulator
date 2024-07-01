package com.bouncy_mehdich.sever.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class Post {
    @JsonProperty("post-id")
    private String PostID;
    @JsonProperty("post-caption")
    private String Caption;
    @JsonProperty("sender-id")
    private String SenderID;
    @JsonProperty("post-date")
    private Date date;
    private ArrayList<Profile> Liked_by = new ArrayList<>();
    private ArrayList<Comment> Comments = new ArrayList<>();

    public Post(String postID, String caption, String senderID, Date date) {
        PostID = postID;
        Caption = caption;
        SenderID = senderID;
        this.date = date;
    }

    public String getPostID() {
        return PostID;
    }

    public String getCaption() {
        return Caption;
    }

    public String getSenderID() {
        return SenderID;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Profile> getLiked_by() {
        return Liked_by;
    }

    public ArrayList<Comment> getComments() {
        return Comments;
    }

    public void setPostID(String postID) {
        PostID = postID;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    public void setSenderID(String senderID) {
        SenderID = senderID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLiked_by(ArrayList<Profile> liked_by) {
        Liked_by = liked_by;
    }

    public void setComments(ArrayList<Comment> comments) {
        Comments = comments;
    }
}
