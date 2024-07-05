package com.bouncy_mehdich.sever.Models;

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
    private Date date;

    public Comment(String postID, String commentText, String senderID, Date date) {
        PostID = postID;
        CommentText = commentText;
        SenderID = senderID;
        this.date = date;
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
        return date;
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
        this.date = date;
    }

}
