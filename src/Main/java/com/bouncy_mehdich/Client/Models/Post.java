package com.bouncy_mehdich.Client.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class Post {
    @JsonProperty("postID")
    private String PostID;
    @JsonProperty("postCaption")
    private String Caption;
    @JsonProperty("senderID")
    private String SenderID;
    @JsonProperty("postDate")
    private Date PostDate;
    @JsonProperty("postLikes")
    private ArrayList<Like> Liked_by = new ArrayList<>();
    @JsonProperty("postComments")
    private ArrayList<Comment> Comments = new ArrayList<>();

    public Post(String postID, String caption, String senderID, Date postDate) {
        PostID = postID;
        Caption = caption;
        SenderID = senderID;
        PostDate = postDate;
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

    public Date getPostDate() {
        return PostDate;
    }

    public ArrayList<Like> getLiked_by() {
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

    public void setPostDate(Date postDate) {
        PostDate = postDate;
    }

    public void setLiked_by(ArrayList<Like> liked_by) {
        Liked_by = liked_by;
    }

    public void setComments(ArrayList<Comment> comments) {
        Comments = comments;
    }
}
