package com.example.nayan.gameverson2.model;

/**
 * Created by Nayan on 4/8/2017.
 */

public class MDownload {
    private int levelId;
    private int subLevelId;
    private int isDownload;
    private int id;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(int isDownload) {
        this.isDownload = isDownload;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
