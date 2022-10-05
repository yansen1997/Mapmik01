package com.example.myapplication;


public class DataModal {
    private String user;
    private String name;
    private String remarks;
    private String code;
    private String longitude;
    private String latitude;

    public DataModal(String user, String name, String remarks, String code, String longitude, String latitude) {
        this.user = user;
        this.name = name;
        this.remarks = remarks;
        this.code = code;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks){
        this.remarks = remarks;
    }

    public String getCode() {
        return code;
    }

    public void setCode(){
        this.code = code;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


}
