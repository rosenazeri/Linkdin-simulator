package com.bouncy_mehdich.sever.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Comment {
    @JsonProperty("comment-id")
    private String CommentID;
    @JsonProperty("text")
    private String CommentText;
    @JsonProperty("sender-id")
    private String SenderID;
    @JsonProperty("comment-date")
    private Date date;
    private Comment Replied_To;

    public Comment(String commentID, String commentText, String senderID, Date date, Comment replied_To) {
        CommentID = commentID;
        CommentText = commentText;
        SenderID = senderID;
        this.date = date;
        Replied_To = replied_To;
    }

    public String getCommentID() {
        return CommentID;
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

    public Comment getReplied_To() {
        return Replied_To;
    }

    public void setCommentID(String commentID) {
        CommentID = commentID;
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

    public void setReplied_To(Comment replied_To) {
        Replied_To = replied_To;
    }
}
