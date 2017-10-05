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
    private String latitude;
    private String longitude;


    public Rat(int Unique_Key,String Date, String Location_Type,int Incident_Zip,String Incident_Address,
               String City,String Borough,String Latitude,String Longitude) {
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

    public int getUniqueKey() {
        return uniqueKey;
    }

    public String getDate() {
        return date;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getIncidentAddress() {
        return incidentAddress;
    }

}
