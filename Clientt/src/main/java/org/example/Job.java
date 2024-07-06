package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Job {

    @JsonProperty("workerID")
    private String WorkerID;
    @JsonProperty("jobTitle")
    private String JobTitle;
    @JsonProperty("employmentType")
    private String EmploymentType;
    @JsonProperty("company")
    private String CompanyName;
    @JsonProperty("location")
    private String WorkplaceLocation;
    @JsonProperty("workplaceType")
    private String WorkLocationType;
    @JsonProperty("isWorking")
    private boolean isWorking;
    @JsonProperty("startDate")
    private Date StartDate;
    @JsonProperty("endDate")
    private Date EndDate;
    @JsonProperty("skills")
    private String Skills;
    @JsonProperty("description")
    private String Description;
    @JsonProperty("doNotify")
    private boolean doNotify;

    public Job(String workerID, String jobTitle, String employmentType, String companyName, String workplaceLocation, String workLocationType, boolean isWorking, Date startDate, Date endDate,String skills, String description, boolean doNotify) {
        WorkerID = workerID;
        JobTitle = jobTitle;
        EmploymentType = employmentType;
        CompanyName = companyName;
        WorkplaceLocation = workplaceLocation;
        WorkLocationType = workLocationType;
        this.isWorking = isWorking;
        StartDate = startDate;
        EndDate = endDate;
        Skills = skills;
        Description = description;
        this.doNotify = doNotify;
    }

    public String getWorkerID() {
        return WorkerID;
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

    public String getSkills() {
        return Skills;
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

    public void setSkills(String skills) {
        Skills = skills;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDoNotify(boolean doNotify) {
        this.doNotify = doNotify;
    }
}
