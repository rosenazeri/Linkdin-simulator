package com.bouncy_mehdich.sever.Models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User {

    @JsonProperty("id")
    private String ID;
    @JsonProperty("pass")
    private String Password;
    @JsonProperty("recovery-string")
    private String RecoveryStr;

    public User(String ID, String password, String recoveryStr) {
        this.ID = ID;
        Password = password;
        RecoveryStr = recoveryStr;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return Password;
    }

    public String getRecoveryStr() {
        return RecoveryStr;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setRecoveryStr(String recoveryStr) {
        RecoveryStr = recoveryStr;
    }
}
