package com.example.nayan.gameverson2.model;

import java.util.ArrayList;

/**
 * Created by NAYAN on 2/8/2017.
 */
public class MAllContent {
    ArrayList<MWords> words=new ArrayList<>();
    private int mid;
    private int content;
    private int logic;
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }



    public int getLogic() {
        return logic;
    }

    public void setLogic(int logic) {
        this.logic = logic;
    }

    private int parentLevelId,sublevelId;

    public int getParentLevelId() {
        return parentLevelId;
    }

    public void setParentLevelId(int parentLevelId) {
        this.parentLevelId = parentLevelId;
    }

    public int getSublevelId() {
        return sublevelId;
    }

    public void setSublevelId(int sublevelId) {
        this.sublevelId = sublevelId;
    }

    private String img;
    private String aud;
    private String txt;
    private String vid;
    private String sen;
    private int presentType,presentId;
    private String click;
    private int bestPoint;
    private int match;
    private int cp;

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public int getBestPoint() {
        return bestPoint;
    }

    public void setBestPoint(int bestPoint) {
        this.bestPoint = bestPoint;
    }

    public int getPresentType() {
        return presentType;
    }

    public void setPresentType(int presentType) {
        this.presentType = presentType;
    }

    public int getPresentId() {
        return presentId;
    }

    public void setPresentId(int presentId) {
        this.presentId = presentId;
    }

    public ArrayList<MWords> getWords() {
        return words;
    }

    public void setWords(ArrayList<MWords> words) {
        this.words = words;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getSen() {
        return sen;
    }

    public void setSen(String sen) {
        this.sen = sen;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }
}
