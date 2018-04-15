package com.example.nayan.gameverson2.model;

import java.util.ArrayList;

/**
 * Created by Nayan on 3/7/2017.
 */

public class MPost {
    private ArrayList<MSubLevel> subLevel;
    private String deviceId, userEmail;

    public ArrayList<MSubLevel> getSubLevel() {
        return subLevel;
    }

    public void setSubLevel(ArrayList<MSubLevel> subLevel) {
        this.subLevel = subLevel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
