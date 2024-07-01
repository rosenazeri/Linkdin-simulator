package com.bouncy_mehdich.sever.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Profile {

    @JsonProperty("id")
    private String ID;
    @JsonProperty("first-name")
    private String FirstName;
    @JsonProperty("last-name")
    private String LastName;
    @JsonProperty("nick-name")
    private String NickName;
    @JsonProperty("bio")
    private String Biography;
    @JsonProperty("country")
    private String Country;
    @JsonProperty("city")
    private String City;
    @JsonProperty("birth")
    private Date Birth;

    public Profile(String ID, String firstName, String lastName, String nickName, String biography, String country, String city, Date birth) {
        this.ID = ID;
        FirstName = firstName;
        LastName = lastName;
        NickName = nickName;
        Biography = biography;
        Country = country;
        City = city;
        Birth = birth;
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

    public Date getBirth() {
        return Birth;
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

    public void setBirth(Date birth) {
        Birth = birth;
    }
}
