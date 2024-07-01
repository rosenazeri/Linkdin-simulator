package com.bouncy_mehdich.sever.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Job {
    @JsonProperty("job-title")
    private String JobTitle;
    @JsonProperty("employment-type")
    private String EmploymentType;
    @JsonProperty("company")
    private String CompanyName;
    @JsonProperty("location")
    private String WorkplaceLocation;
    @JsonProperty("workplace-type")
    private String WorkLocationType;
    @JsonProperty("isWorking")
    private boolean isWorking;
    @JsonProperty("start-date")
    private Date StartDate;
    @JsonProperty("end-date")
    private Date EndDate;
    @JsonProperty("description")
    private String Description;
    // TODO : Skills(0-5)
    private boolean doNotify;

    public Job(String jobTitle, String employmentType, String companyName, String workplaceLocation, String workLocationType, boolean isWorking, Date startDate, Date endDate, String description, boolean doNotify) {
        JobTitle = jobTitle;
        EmploymentType = employmentType;
        CompanyName = companyName;
        WorkplaceLocation = workplaceLocation;
        WorkLocationType = workLocationType;
        this.isWorking = isWorking;
        StartDate = startDate;
        EndDate = endDate;
        Description = description;
        this.doNotify = doNotify;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public String getEmploymentType() {
        return EmploymentType;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getWorkplaceLocation() {
        return WorkplaceLocation;
    }

    public String getWorkLocationType() {
        return WorkLocationType;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public String getDescription() {
        return Description;
    }

    public boolean isDoNotify() {
        return doNotify;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public void setEmploymentType(String employmentType) {
        EmploymentType = employmentType;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public void setWorkplaceLocation(String workplaceLocation) {
        WorkplaceLocation = workplaceLocation;
    }

    public void setWorkLocationType(String workLocationType) {
        WorkLocationType = workLocationType;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDoNotify(boolean doNotify) {
        this.doNotify = doNotify;
    }
}
