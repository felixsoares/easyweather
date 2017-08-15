package com.felix.easywheater.code.model;

/**
 * Created by user on 15/08/2017.
 */

public class Weather {

    private Double temp_c;
    private Double wind_mph;
    private Double wind_kph;
    private Double feelslike_c;
    private Double temp_f;
    private Double feelslike_f;
    private Integer is_day;
    private Double humidity;
    private Condition condition;

    public Weather(){

    }

    public Double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(Double temp_c) {
        this.temp_c = temp_c;
    }

    public Double getFeelslike_c() {
        return feelslike_c;
    }

    public void setFeelslike_c(Double feelslike_c) {
        this.feelslike_c = feelslike_c;
    }

    public Double getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(Double temp_f) {
        this.temp_f = temp_f;
    }

    public Double getFeelslike_f() {
        return feelslike_f;
    }

    public void setFeelslike_f(Double feelslike_f) {
        this.feelslike_f = feelslike_f;
    }

    public Integer getIs_day() {
        return is_day;
    }

    public void setIs_day(Integer is_day) {
        this.is_day = is_day;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Double getWind_kph() {
        return wind_kph;
    }

    public Double getWind_mph() {
        return wind_mph;
    }

    public void setWind_kph(Double wind_kph) {
        this.wind_kph = wind_kph;
    }

    public void setWind_mph(Double wind_mph) {
        this.wind_mph = wind_mph;
    }
}
