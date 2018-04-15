package com.example.nayan.gameverson2.model;

import java.util.ArrayList;

/**
 * Created by NAYAN on 9/26/2016.
 */

public class MSubLevel {
    private int parentId;
    private int logic;
    private String howto;
    private String parentName;
    private int lid, bestPoint;
    private String name;
    private String coins_price;
    private String no_of_coins;
    private int unlockNextLevel;
    private int content;
    private int color;
    private int IsDownload;

    public int getIsDownload() {
        return IsDownload;
    }

    public void setIsDownload(int isDownload) {
        IsDownload = isDownload;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }
    public int getLogic() {
        return logic;
    }

    public void setLogic(int logic) {
        this.logic = logic;
    }

    public String getHowto() {
        return howto;
    }

    public void setHowto(String howto) {
        this.howto = howto;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getBestPoint() {
        return bestPoint;
    }

    public void setBestPoint(int bestPoint) {
        this.bestPoint = bestPoint;
    }

    public int getUnlockNextLevel() {
        return unlockNextLevel;
    }

    public void setUnlockNextLevel(int unlockNextLevel) {
        this.unlockNextLevel = unlockNextLevel;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getNo_of_coins() {
        return no_of_coins;
    }

    public void setNo_of_coins(String no_of_coins) {
        this.no_of_coins = no_of_coins;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoins_price() {
        return coins_price;
    }

    public void setCoins_price(String coins_price) {
        this.coins_price = coins_price;
    }


}
