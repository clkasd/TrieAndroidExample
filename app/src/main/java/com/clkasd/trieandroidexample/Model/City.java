package com.clkasd.trieandroidexample.Model;

/**
 * Created by aykutcelik on 21.12.2017.
 */

public class City {

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    double lng;
    double lat;
    String country;
    String name;
}
