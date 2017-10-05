package com.example.jay.u_dirty_rat;

/**
 * Created by jw_chung on 10/5/17.
 * this class will allow us to handle
 * each rat report as a single object.
 */

public class Rat {
    private int uniqueKey;
    private String date;
    private String locationType;
    private int incidentZip;
    private String incidentAddress;
    private String city;
    private String borough;
    private double latitude;
    private double longitude;


    public Rat(int Unique_Key,String Date, String Location_Type,int Incident_Zip,String Incident_Address,
               String City,String Borough,double Latitude,double Longitude) {
        this.uniqueKey = Unique_Key;
        this.date = Date;
        this.locationType = Location_Type;
        this.incidentZip = Incident_Zip;
        this.incidentAddress = Incident_Address;
        this.city = City;
        this.borough = Borough;
        this.latitude = Latitude;
        this.longitude = Longitude;
    }

    //getter method for rat report.

    public int getUniqueKey() {
        return uniqueKey;
    }

    public String getDate() {
        return date;
    }

    public String getLocationType() { return locationType;}

    public int getIncidentZip() { return  incidentZip;}

    public String getIncidentAddress() {
        return incidentAddress;
    }

    public String getCity() { return city;}

    public String getBorough() { return borough;}

    public double getLatitude() { return latitude;}

    public double getLongitude() { return longitude;}

    //setter method for rat report.


    public void setUniqueKey(int uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public void setIncidentZip(int incidentZip) {
        this.incidentZip = incidentZip;
    }

    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }
}
