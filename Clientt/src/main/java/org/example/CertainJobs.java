package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CertainJobs {
    @JsonProperty("title")
    private String title ;
    @JsonProperty("employment")
    private String employment ;
    @JsonProperty("company")
    private String company ;
    @JsonProperty("workPlace")
    private String workPlace ;
    @JsonProperty("workPlaceType")
    private String workPlaceType ;
    @JsonProperty("startWorking")
    private Date startWorking ;
    @JsonProperty("finishedWorking")
    private Date finishedWorking ;
    @JsonProperty("explanation")
    private String explanation ;
    @JsonProperty("skills")
    private String skills ;

    public CertainJobs(String title, String employment, String company, String workPlace, String workPlaceType, Date startWorking, Date finishedWorking, String explanation, String skills) {
        this.title = title;
        this.employment = employment;
        this.company = company;
        this.workPlace = workPlace;
        this.workPlaceType = workPlaceType;
        this.startWorking = startWorking;
        this.finishedWorking = finishedWorking;
        this.explanation = explanation;
        this.skills = skills;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getWorkPlaceType() {
        return workPlaceType;
    }

    public void setWorkPlaceType(String workPlaceType) {
        this.workPlaceType = workPlaceType;
    }

    public Date getStartWorking() {
        return startWorking;
    }

    public void setStartWorking(Date startWorking) {
        this.startWorking = startWorking;
    }

    public Date getFinishedWorking() {
        return finishedWorking;
    }

    public void setFinishedWorking(Date finishedWorking) {
        this.finishedWorking = finishedWorking;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }
}
