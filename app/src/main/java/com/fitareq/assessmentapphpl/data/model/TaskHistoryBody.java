package com.fitareq.assessmentapphpl.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskHistoryBody {
    @SerializedName("contact_number")
    @Expose
    private String contactNumber;

    public TaskHistoryBody(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
