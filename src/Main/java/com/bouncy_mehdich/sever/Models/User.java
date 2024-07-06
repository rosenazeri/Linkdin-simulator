package Main.java.com.bouncy_mehdich.sever.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class User {

    @JsonProperty("id")
    private String ID;
    @JsonProperty("firstName")
    private String FirstName;
    @JsonProperty("lastName")
    private String LastName;
    @JsonProperty("email")
    private String Email;
    @JsonProperty("password")
    private String Password;
    @JsonProperty("recovery-string")
    private String RecoveryStr;
    @JsonProperty("createTime")
    private Date CreateTime;

    public User(String ID,String firstName, String lastName, String email, String password, String recoveryStr) {
        this.ID = ID;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Password = password;
        RecoveryStr = recoveryStr;
        CreateTime = new Date(System.currentTimeMillis());
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getRecoveryStr() {
        return RecoveryStr;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setRecoveryStr(String recoveryStr) {
        RecoveryStr = recoveryStr;
    }

    @Override
    public String toString() {
        return "User [id="
                + ID
                + ", firstName="
                + FirstName
                + ", lastName="
                + LastName
                + ", email="
                + Email
                + ", password="
                + Password
                + ", recovery-string="
                + RecoveryStr
                + ", createTime="

                + "" + "]";
    }

}
