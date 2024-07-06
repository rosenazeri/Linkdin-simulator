package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Profile {

    @JsonProperty("id")
    private String ID;
    @JsonProperty("nickName")
    private String NickName;
    @JsonProperty("bio")
    private String Biography;
    @JsonProperty("country")
    private String Country;
    @JsonProperty("city")
    private String City;
    @JsonProperty("herfe")
    private String Herfe;
    @JsonProperty("birth")
    private Date Birth;

    public Profile(String ID, String nickName, String biography, String country, String city, String herfe, Date birth) {
        this.ID = ID;
        NickName = nickName;
        Biography = biography;
        Country = country;
        City = city;
        Herfe = herfe;
        Birth = birth;
    }

    public String getID() {
        return ID;
    }

    public String getNickName() {
        return NickName;
    }

    public String getBiography() {
        return Biography;
    }

    public String getCountry() {
        return Country;
    }

    public String getCity() {
        return City;
    }

    public String getHerfe() {
        return Herfe;
    }

    public Date getBirth() {
        return Birth;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setBiography(String biography) {
        Biography = biography;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setHerfe(String herfe) {
        Herfe = herfe;
    }

    public void setBirth(Date birth) {
        Birth = birth;
    }

}
