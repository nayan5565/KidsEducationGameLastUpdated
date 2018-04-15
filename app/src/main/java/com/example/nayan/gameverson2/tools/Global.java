package com.example.nayan.gameverson2.tools;

import com.example.nayan.gameverson2.model.MAllContent;
//import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.model.MDownload;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MPost;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.model.MWords;

import java.util.ArrayList;

/**
 * Created by NAYAN on 11/14/2016.
 */
public class Global {
    public static final String IMAGE_URL = "http://www.radhooni.com/content/match_game/v1/images/";
    public static final String BASE_SOUND_URL = "http://www.radhooni.com/content/match_game/v1/sounds/";
    public static final String API_LEVELS = "levels.php";
    public static final String API_CONTENTS = "contents.php";
    public static final String API_MATH = "contents_math.php";
    public static final String API_BANGLA_MATH = "contents_ongko.php";
    public static final String API_ENGLISH = "contents_english.php";
    public static final String API_BANGLA = "contents_bangla.php";
    public static final String BASE_URL = "http://www.radhooni.com/content/match_game/v1/";

    public static int levelId;
    public static String levelName;
    public static String subLevelName;
    public static int logic;
    public static int CONTENT,CONTENT_POS;
    public static String internetAlert = "No Internet Connection. Please at first need internet connected then exit from app and again open it.";
    public static String parentLevelName;
    public static int SUB_INDEX_POSITION;
    public static int pos;
    public static String ALTER_URL = "";
    public static int subLevelId;
    public static int LEVEL_DOWNLOAD = 5;
    public static int IS_DOWNLOAD = 0;
    public static int totalPoint;
    public static int isSavePoint;
    public static int ALL_TOTAL_POINT;
    public static int GAME_INDEX_POSITION;
    public static ArrayList<MSubLevel> parentName;
    public static String uriBangla = "@drawable/green_panel";
    public static String uriOngko = "@drawable/yellow_panel";
    public static String uriEnglish = "@drawable/red_panel";
    public static String uriMath = "@drawable/violet_panel";
    public static String IMAGE_OPEN = "one", IMAGE_OFF = "two";
    public static String ASSETS_DOWNLOAD_MASSAGE = "downloaded";
    public static String CONVERT_NUM = "downloaded";
    public static ArrayList<MSubLevel> mSubLevelArrayList;
    public static ArrayList<MAllContent> mAllContentArrayList;
    public static ArrayList<MLevel> levels;
    public static ArrayList<MDownload> mDownloads;
    public static ArrayList<MPost> gameStatus;
//    public static ArrayList<MContents> contents;
    public static ArrayList<MAllContent> English;
    public static ArrayList<MWords> English_words;
    public static ArrayList<MWords> BANGLA_words;
    public static ArrayList<MWords> MATH_words;
    public static ArrayList<MAllContent> Maths;
    public static ArrayList<MWords> BANGLA_MATH_words;
    public static ArrayList<MAllContent> BANGLA_Maths;
    public static ArrayList<MAllContent> BANGLA;
    public static int present_content;
    public static int startDownAll;
    public static int startDownBan;
    public static int startDownOngk;
    public static int startDownEng;
    public static int startDownMath;
    public static int maximumContentOfBangla;
    public static int maximumContentOfOngko;
    public static int maximumContentOfEnglish;
    public static int maximumContentOfMath;
    public static ArrayList<String> URLS;
    public static int popUp;
    public static String ADMIN = "dino.araf@gmail.com";
    public static String SUBJECT = "your_subject";
    public static String TEXT = "your_text";


}
