package com.felix.easywheater.scene.main.model;

import com.felix.easywheater.code.model.City;
import com.felix.easywheater.code.model.Weather;

/**
 * Created by user on 15/08/2017.
 */

public class ViewModelMain {

    public Boolean status;
    public String message;
    public City location;
    public Weather current;

    public ViewModelMain() {

    }

    public ViewModelMain(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public City getLocation() {
        return location;
    }

    public Weather getCurrent() {
        return current;
    }

    public void setCurrent(Weather current) {
        this.current = current;
    }

    public void setLocation(City location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
