package com.example.ahsan.agentmommymessageapp;

/**
 * Created by AHSAN on 7/8/2018.
 */

public class Message {
    String latitude;
    String longitude;
    String message;
    String zipcode;

    public Message(String latitude, String longitude, String message, String zipcode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.message = message;
        this.zipcode = zipcode;
    }

    public Message(){

    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
