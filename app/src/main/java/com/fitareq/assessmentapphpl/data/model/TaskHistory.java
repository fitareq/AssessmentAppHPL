package com.fitareq.assessmentapphpl.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskHistory {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("entryDate")
    @Expose
    private String entryDate;
    @SerializedName("purpose")
    @Expose
    private String purpose;

    public TaskHistory(String id, String title, String details, String contactNumber, String entryDate, String purpose) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.contactNumber = contactNumber;
        this.entryDate = entryDate;
        this.purpose = purpose;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
