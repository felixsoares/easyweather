package com.felix.easywheater.code.model;

/**
 * Created by user on 15/08/2017.
 */

public class Condition {

    private String text;
    private String icon;
    private Integer code;

    public Condition(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
