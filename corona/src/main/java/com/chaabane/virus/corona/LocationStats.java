package com.chaabane.virus.corona;

public class LocationStats {
    private String state;
    private String country;
    private int lastTotalCases;
    private double latitude;
    private double longtitude;

    public LocationStats() {
    }

    public LocationStats(String state, String country, int lastTotalCases, double latitude, double longtitude) {
        this.state = state;
        this.country = country;
        this.lastTotalCases = lastTotalCases;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLastTotalCases() {
        return lastTotalCases;
    }

    public void setLastTotalCases(int lastTotalCases) {
        this.lastTotalCases = lastTotalCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", lastTotalCases=" + lastTotalCases +
                '}';
    }
}
