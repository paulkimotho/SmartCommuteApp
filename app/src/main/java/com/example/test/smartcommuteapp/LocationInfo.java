package com.example.test.smartcommuteapp;

public class LocationInfo {

    public String locationName;
    public String address;

    public LocationInfo() {
    }

    public LocationInfo(String locationName, String address) {
        this.locationName = locationName;
        this.address = address;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
