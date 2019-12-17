package com.example.project_rescuegroups.model;

public class Organization
{
    private int organizationID;
    private String organizationName;
    private String organizationAddress;
    private String organizationCity;
    private String organizationState;
    private String organizationZip;
    private String organizationCountry;
    private String organizationPhone;
    private String organizationEmail;

    public Organization(int organizationID, String organizationName, String organizationAddress, String organizationCity, String organizationState, String organizationZip, String organizationCountry, String organizationPhone, String organizationEmail) {
        this.organizationID = organizationID;
        this.organizationName = organizationName;
        this.organizationAddress = organizationAddress;
        this.organizationCity = organizationCity;
        this.organizationState = organizationState;
        this.organizationZip = organizationZip;
        this.organizationCountry = organizationCountry;
        this.organizationPhone = organizationPhone;
        this.organizationEmail = organizationEmail;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getOrganizationCity() {
        return organizationCity;
    }

    public void setOrganizationCity(String organizationCity) {
        this.organizationCity = organizationCity;
    }

    public String getOrganizationState() {
        return organizationState;
    }

    public void setOrganizationState(String organizationState) {
        this.organizationState = organizationState;
    }

    public String getOrganizationZip() {
        return organizationZip;
    }

    public void setOrganizationZip(String organizationZip) {
        this.organizationZip = organizationZip;
    }

    public String getOrganizationCountry() {
        return organizationCountry;
    }

    public void setOrganizationCountry(String organizationCountry) {
        this.organizationCountry = organizationCountry;
    }

    public String getOrganizationPhone() {
        return organizationPhone;
    }

    public void setOrganizationPhone(String organizationPhone) {
        this.organizationPhone = organizationPhone;
    }

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }
}
