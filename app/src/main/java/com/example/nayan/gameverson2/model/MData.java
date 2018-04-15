package com.example.nayan.gameverson2.model;

/**
 * Created by NAYAN on 8/3/2016.
 */
public class MData {
    int id;
    int popUp;
    private int levelId,subLevelId,isSavePoint;

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getSubLevelId() {
        return subLevelId;
    }

    public void setSubLevelId(int subLevelId) {
        this.subLevelId = subLevelId;
    }

    public int getIsSavePoint() {
        return isSavePoint;
    }

    public void setIsSavePoint(int isSavePoint) {
        this.isSavePoint = isSavePoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPopUp() {
        return popUp;
    }

    public void setPopUp(int type) {
        this.popUp = type;
    }
}
