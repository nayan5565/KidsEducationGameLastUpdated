package com.example.nayan.gameverson2.model;

/**
 * Created by NAYAN on 2/8/2017.
 */
public class MWords {
    private int wid;
    private String wletter;
    private String wword;
    private String wimg;
    private String wsound;
    private int contentId;

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getWletter() {
        return wletter;
    }

    public void setWletter(String wletter) {
        this.wletter = wletter;
    }

    public String getWword() {
        return wword;
    }

    public void setWword(String wword) {
        this.wword = wword;
    }

    public String getWimg() {
        return wimg;
    }

    public void setWimg(String wimg) {
        this.wimg = wimg;
    }

    public String getWsound() {
        return wsound;
    }

    public void setWsound(String wsound) {
        this.wsound = wsound;
    }
}
