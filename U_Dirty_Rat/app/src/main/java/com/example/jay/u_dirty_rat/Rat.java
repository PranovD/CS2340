package com.example.jay.u_dirty_rat;

/**
 * Created by jw_chung on 10/5/17.
 * this class will allow us to handle
 * each rat report as a single object. (cluster of strings)
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

    /**
     * default constructor to
     * get the data from firebase.
     */
    public Rat() {
        this.uniqueKey = -1;
        this.date = "TBD";
        this.locationType = "TBD";
        this.incidentZip = 00000;
        this.incidentAddress = "TBD";
        this.city = "TBD";
        this.borough = "TBD";
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    //constructor with inputs

    /**
     * constructor with string inputs
     * @param Unique_Key the unique key of the rat
     * @param Date the date of the rat sighting
     * @param Location_Type the location type of where the rat was sighted
     * @param Incident_Zip the zip code of the rat sighting
     * @param Incident_Address the address of the rat sighting
     * @param City the city where the rat was spotted
     * @param Borough the borough of the rat sighting
     * @param Latitude the latitude of where the rat was spotted
     * @param Longitude the longitude of where the rat was spotted
     */
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

    /**
     * getter method for unique key.
     * @return unique key of the object
     */
    public int getUniqueKey() { return uniqueKey; }

    /**
     * getter method for date.
     * @return date of the object
     */
    public String getDate() {
        return date;
    }

    /**
     * getter method for location type.
     * @return location type of the object
     */
    public String getLocationType() { return locationType;}

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
    public String getBorough() { return borough;}

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
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * setter method to change location type
     * @param locationType : new location type
     */
    public void setLocationType(String locationType) {
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
    public void setBorough(String borough) {
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
