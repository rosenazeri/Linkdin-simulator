package Main.java.com.bouncy_mehdich.sever.Models;

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
    private int Likes;
    @JsonProperty("postComments")
    private int Comments;

    public Post(String postID, String caption, String senderID, Date postDate) {
        PostID = postID;
        Caption = caption;
        SenderID = senderID;
        PostDate = postDate;
        Comments = 0;
        Likes = 0;
    }

    public Post(String postID, String caption, String senderID, Date postDate, int likes, int comments) {
        PostID = postID;
        Caption = caption;
        SenderID = senderID;
        PostDate = postDate;
        Likes = likes;
        Comments = comments;
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

    public int getLikes() {
        return Likes;
    }

    public int getComments() {
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

    public void increaseLikes() {
        Likes++;
    }

    public void decreaseLikes() {
        Likes--;
    }

    public void decreaseComments() {
        Comments--;
    }

    public void increaseComments() {
        Comments++;
    }
}
