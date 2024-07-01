package com.bouncy_mehdich.sever.Models;

public class Message {
    private String MessageText;
    private String SenderID;
    private String ReceiverID;

    public Message(String messageText, String senderID, String receiverID) {
        MessageText = messageText;
        SenderID = senderID;
        ReceiverID = receiverID;
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

    public void setMessageText(String messageText) {
        MessageText = messageText;
    }

    public void setSenderID(String senderID) {
        SenderID = senderID;
    }

    public void setReceiverID(String receiverID) {
        ReceiverID = receiverID;
    }
}
