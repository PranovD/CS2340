package com.example.jay.u_dirty_rat;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jw_chung on 10/5/17.
 * this class will allow us to handle
 * each rat report as a single object. (cluster of strings)
 */

public class Rat {

    public enum Borough implements Serializable {
        MANHATTAN ("MANHATTAN"),
        STATEN_ISLAND ("STATEN ISLAND"),
        QUEENS ("QUEENS"),
        BROOKLYN ("BROOKLYN"),
        BRONX ("BRONX");

        private final String abbrev;

        private Borough(String value) {
            this.abbrev = value;
        }

        public String toString() {
            return this.abbrev;
        }
    }

    public enum LocationType implements Serializable {
        DWELLING ("1-2 Family Dwelling"),
        APARTMENT ("3+ Family Apt. Building"),
        MIXED ("3+ Family Mixed Use Building"),
        COMMERCIAL ("Commercial Building"),
        LOT ("Vacant Lot"),
        CONSTRUCTION ("Construction Site"),
        HOSPITAL ("Hospital"),
        SEWER ("Catch Basin/Sewer");

        private final String abbrev;

        private LocationType(String value) {
            this.abbrev = value;
        }

        public String toString() {
            return this.abbrev;
        }
    }
    private int uniqueKey;
    private Date date;
    private LocationType locationType;
    private int incidentZip;
    private String incidentAddress;
    private String city;
    private Borough borough;
    private double latitude;
    private double longitude;

    /**
     * default constructor to
     * get the data from firebase.
     */
    public Rat() {
        this.uniqueKey = -1;
        this.date = new Date();
        this.locationType = LocationType.DWELLING;
        this.incidentZip = 00000;
        this.incidentAddress = "TBD";
        this.city = "TBD";
        this.borough = Borough.MANHATTAN;
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    //constructor with inputs

    /**
     * constructor with string inputs
     * @param Unique_Key
     * @param date
     * @param Location_Type
     * @param Incident_Zip
     * @param Incident_Address
     * @param City
     * @param Borough
     * @param Latitude
     * @param Longitude
     */
    public Rat(int Unique_Key,Date date, LocationType Location_Type,int Incident_Zip,String Incident_Address,
               String City,Borough borough,double Latitude,double Longitude) {
        this.uniqueKey = Unique_Key;
        this.date = date;
        this.locationType = Location_Type;
        this.incidentZip = Incident_Zip;
        this.incidentAddress = Incident_Address;
        this.city = City;
        this.borough = borough;
        this.latitude = Latitude;
        this.longitude = Longitude;
    }

    /**
     * getter method for unique key.
     * @return unique key of the object
     */
    public int getUniqueKey() { return uniqueKey; }

    /**
     * getter method for date.
     * @return date of the object
     */
    public Date getDate() {
        return date;
    }

    /**
     * getter method for location type.
     * @return location type of the object
     */
    public LocationType getLocationType() { return locationType;}

    /**
     * getter method for incident zip.
     * @return incident zip of the object.
     */
    public int getIncidentZip() { return  incidentZip;}

    /**
     * getter method for incident address.
     * @return incident address of the object.
     */
    public String getIncidentAddress() {
        return incidentAddress;
    }

    /**
     * getter method for city
     * @return city of the object.
     */
    public String getCity() { return city;}

    /**
     * getter method for borough
     * @return borough of the object.
     */
    public Borough getBorough() { return borough;}

    /**
     * getter method for latitude
     * @return latitude of the object.
     */
    public double getLatitude() { return latitude;}

    /**
     * getter method for longitude
     * @return longitude of the object.
     */
    public double getLongitude() { return longitude;}

    /**
     * setter method to change unique key
     * @param uniqueKey : new unique key
     */
    public void setUniqueKey(int uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    /**
     * setter method to change date
     * @param date : new date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * setter method to change location type
     * @param locationType : new location type
     */
    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    /**
     * setter method to change incident zip
     * @param incidentZip : incident zip
     */
    public void setIncidentZip(int incidentZip) {
        this.incidentZip = incidentZip;
    }

    /**
     * setter method to change incident address
     * @param incidentAddress : incident address
     */
    public void setIncidentAddress(String incidentAddress) {
        this.incidentAddress = incidentAddress;
    }

    /**
     * setter method to change city
     * @param city : new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * setter method to change latitude
     * @param latitude : new latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * setter method to change longitude
     * @param longitude : new longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * setter method to change borough
     * @param borough : new borough
     */
    public void setBorough(Borough borough) {
        this.borough = borough;
    }

    /**
     * toString to print
     * @return string with unique key and date
     */
    @Override
    public String toString() {
        return this.uniqueKey + ", " + this.date;
    }
}
