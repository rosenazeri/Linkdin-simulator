package com.bouncy_mehdich.Client.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Comment {
    @JsonProperty("postID")
    private String PostID;
    @JsonProperty("text")
    private String CommentText;
    @JsonProperty("senderID")
    private String SenderID;
    @JsonProperty("commentDate")
    private Date Date;

    public Comment(String postID, String commentText, String senderID) {
        PostID = postID;
        CommentText = commentText;
        SenderID = senderID;
        Date = new Date(System.currentTimeMillis());
    }

    public Comment(String postID, String commentText, String senderID, java.util.Date date) {
        PostID = postID;
        CommentText = commentText;
        SenderID = senderID;
        Date = date;
    }

    public String getPostID() {
        return PostID;
    }

    public String getCommentText() {
        return CommentText;
    }

    public String getSenderID() {
        return SenderID;
    }

    public Date getDate() {
        return Date;
    }

    public void setPostID(String postID) {
        PostID = postID;
    }

    public void setCommentText(String commentText) {
        CommentText = commentText;
    }

    public void setSenderID(String senderID) {
        SenderID = senderID;
    }

    public void setDate(Date date) {
        Date = date;
    }

}
