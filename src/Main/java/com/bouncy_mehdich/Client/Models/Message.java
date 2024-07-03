package com.bouncy_mehdich.Client.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Message {

    @JsonProperty("message")
    private String MessageText;
    @JsonProperty("senderID")
    private String SenderID;
    @JsonProperty("receiverID")
    private String ReceiverID;
    @JsonProperty("messageDate")
    private Date MessageDate;

    public Message(String messageText, String senderID, String receiverID) {
        MessageText = messageText;
        SenderID = senderID;
        ReceiverID = receiverID;
        MessageDate = new Date(System.currentTimeMillis());
    }

    public Message(String messageText, String senderID, String receiverID, Date messageDate) {
        MessageText = messageText;
        SenderID = senderID;
        ReceiverID = receiverID;
        MessageDate = messageDate;
    }

    public String getMessageText() {
        return MessageText;
    }

    public String getSenderID() {
        return SenderID;
    }

    public String getReceiverID() {
        return ReceiverID;
    }

    public Date getMessageDate() {
        return MessageDate;
    }

    public void setMessageText(String messageText) {
        MessageText = messageText;
    }

    public void setSenderID(String senderID) {
        SenderID = senderID;
    }

    public void setReceiverID(String receiverID) {
        ReceiverID = receiverID;
    }

    public void setMessageDate(Date messageDate) {
        MessageDate = messageDate;
    }
}
