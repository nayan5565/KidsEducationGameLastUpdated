package com.example.nayan.gameverson2.model;

/**
 * Created by NAYAN on 11/16/2016.
 */
public class MLock {
    private int unlockNextLevel;
    private int id;
    private int level_id;
    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSub_level_id() {
        return sub_level_id;
    }

    public void setSub_level_id(int sub_level_id) {
        this.sub_level_id = sub_level_id;
    }

    public int getLevel_id() {
        return level_id;
    }

    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }

    private int sub_level_id;
    private int bestPoint;
    private int total_pont;
    private int all_total_point;
    private int popup;
    private int isSavePoint;

    public int getIsSavePoint() {
        return isSavePoint;
    }

    public void setIsSavePoint(int isSavePoint) {
        this.isSavePoint = isSavePoint;
    }

    public int getPopup() {
        return popup;
    }

    public void setPopup(int popup) {
        this.popup = popup;
    }

    public int getAll_total_point() {
        return all_total_point;
    }

    public void setAll_total_point(int all_total_point) {
        this.all_total_point = all_total_point;
    }


    public int getTotal_pont() {
        return total_pont;
    }

    public void setTotal_pont(int total_pont) {
        this.total_pont = total_pont;
    }


    public int getBestPoint() {
        return bestPoint;
    }

    public void setBestPoint(int bestPoint) {
        this.bestPoint = bestPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnlockNextLevel() {
        return unlockNextLevel;
    }

    public void setUnlockNextLevel(int unlockNextLevel) {
        this.unlockNextLevel = unlockNextLevel;
    }
}
