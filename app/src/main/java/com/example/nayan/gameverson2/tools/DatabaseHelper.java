package com.example.nayan.gameverson2.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.nayan.gameverson2.model.MAllContent;
//import com.example.nayan.gameverson2.model.MContents;
import com.example.nayan.gameverson2.model.MColor;
import com.example.nayan.gameverson2.model.MData;
import com.example.nayan.gameverson2.model.MDownload;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.model.MWords;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by NAYAN on 8/24/2016.
 */
public class DatabaseHelper {
    private static final String DATABASE_NAME = "game.db";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_LEVEL_TABLE = "level_db";
    private static final String DATABASE_DOWNLOAD_TABLE = "download_tb";
    private static final String DATABASE_ALL_CONTENTS_TABLE = "all_contents";
    private static final String DATABASE_IS_SAVE_POINT_TABLE = "is_save_point";
    private static final String DATABASE_LOCK_TABLE = "lock_tb";
    private static final String DATABASE_SUB_LEVEL_TABLE = "sub";
    private static final String DATABASE_ALL_WORDS_TABLE = "all_words_tb";
    private static final String DATABASE_COLOR_TABLE = "color_tb";
    private static final String KEY_WORDS_ID = "words_id";
    private static final String KEY_WORDS_CONTENTS_ID = "words_contents_id";
    private static final String KEY_WORDS_LETTER = "words_letter";
    private static final String KEY_WORDS_Word = "words_word";
    private static final String KEY_WORDS_IMG = "words_img";
    private static final String KEY_WORDS_SOUND = "words_sound";
    private static final String KEY_HOW_TO = "how_to";
    private static final String KEY_IS_DOWNLOAD = "is__download";
    private static final String KEY_URL = "url";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_POPUP = "pop_up";
    private static final String KEY_PRESENT_POINT = "present_point";
    private static final String KEY_COLOR = "color_c";
    private static final String KEY_UPDATE_DATE = "update_date";
    private static final String KEY_TOTAL_S_LEVEL = "total_slevel";
    private static final String KEY_DIFFICULTY = "difficulty";
    private static final String KEY_LEVEL_ID = "lid";
    private static final String KEY_SUB_LEVEL_ID = "s_lid";
    private static final String KEY_IS_SAVE_POINT = "save_point";
    private static final String KEY_MODEL_ID = "mid";
    private static final String KEY_LOCK_ID = "loid";
    private static final String KEY_UNLOCK = "un_lock";
    private static final String KEY_TOTAL_POINT = "lock_total_point";
    private static final String KEY_ALL_TOTAL_POINT = "lock_all_total_point";
    private static final String KEY_PARENT_ID = "pid";
    private static final String KEY_PARENT_NAME = "pNm";
    private static final String KEY_SEN = "sen";
    private static final String KEY_IMAGE = "img";
    private static final String KEY_SOUNDS = "aud";
    private static final String KEY_NAME = "name";
    private static final String KEY_VIDEO = "vid";
    private static final String KEY_TEXT = "txt";
    private static final String KEY_COINS_PRICE = "coins_price";
    private static final String KEY_NO_OF_COINS = "no_of_coins";
    private static final String KEY_LOGIC = "k_Logic";
    private static final String KEY_DOWNLOAD = "k_download";
    private static final String KEY_PRESENT_ID = "present_id";
    private static final String KEY_PRESENT_TYPE = "present_type";
    private static final String KEY_POINT = "best_point";
    private static final String KEY_LEVEL_WIN_COUNT = "win_count";
    private static final String DATABASE_CREATE_LEVEL_TABLE = "create table if not exists "
            + DATABASE_LEVEL_TABLE + "("
            + KEY_LEVEL_ID + " integer primary key, "
            + KEY_NAME + " text, "
            + KEY_UPDATE_DATE + " text, "
            + KEY_HOW_TO + " text, "
            + KEY_DIFFICULTY + " integer, "
            + KEY_POINT + " integer, "
            + KEY_LEVEL_WIN_COUNT + " integer, "
            + KEY_TOTAL_S_LEVEL + " text)";
    private static final String DATABASE_CREATE_ALL_CONTENTS_TABLE = "create table if not exists "
            + DATABASE_ALL_CONTENTS_TABLE + "("
            + KEY_CONTENT + " integer, "
            + KEY_LEVEL + " integer, "
            + KEY_MODEL_ID + " integer primary key, "
            + KEY_PRESENT_ID + " integer, "
            + KEY_PARENT_ID + " integer, "
            + KEY_SUB_LEVEL_ID + " integer, "
            + KEY_PRESENT_TYPE + " integer, "
            + KEY_LOGIC + " integer, "
            + KEY_IMAGE + " text, "
            + KEY_SEN + " text, "
            + KEY_TEXT + " text, "
            + KEY_VIDEO + " text, "
            + KEY_SOUNDS + " text)";
    private static final String DATABASE_CREATE_SUB_LEVEL_TABLE = "create table if not exists "
            + DATABASE_SUB_LEVEL_TABLE + "("
            + KEY_SUB_LEVEL_ID + " integer primary key, "
            + KEY_NAME + " text, "
            + KEY_PARENT_ID + " integer, "
            + KEY_CONTENT + " integer, "
            + KEY_LOGIC + " integer, "
            + KEY_DOWNLOAD + " integer, "
            + KEY_UNLOCK + " integer, "
            + KEY_POINT + " integer, "
            + KEY_COLOR + " integer, "
            + KEY_POPUP + " integer, "
            + KEY_PARENT_NAME + " text, "
            + KEY_HOW_TO + " text, "
            + KEY_COINS_PRICE + " text, "
            + KEY_NO_OF_COINS + " text)";
    private static final String DATABASE_CREATE_COLOR_TABLE = "create table if not exists "
            + DATABASE_COLOR_TABLE + "("
            + KEY_WORDS_ID + " integer primary key autoincrement, "
            + KEY_LEVEL_ID + " integer , "
            + KEY_COLOR + " integer , "
            + KEY_SUB_LEVEL_ID + " integer)";
    private static final String DATABASE_CREATE_ALL_WORDS_TABLE = "create table if not exists "
            + DATABASE_ALL_WORDS_TABLE + "("
            + KEY_WORDS_ID + " integer primary key, "
            + KEY_WORDS_CONTENTS_ID + " integer, "
            + KEY_WORDS_IMG + " text, "
            + KEY_WORDS_LETTER + " text, "
            + KEY_WORDS_SOUND + " text, "
            + KEY_WORDS_Word + " text)";
    private static final String DATABASE_CREATE_LOCK_TABLE = "create table if not exists "
            + DATABASE_LOCK_TABLE + "("
            + KEY_LOCK_ID + " integer primary key autoincrement, "
            + KEY_POINT + " integer, "
            + KEY_UNLOCK + " integer, "
            + KEY_TOTAL_POINT + " integer, "
            + KEY_COLOR + " integer, "
            + KEY_POPUP + " integer, "
            + KEY_IS_SAVE_POINT + " integer, "
            + KEY_LEVEL_ID + " integer, "
            + KEY_SUB_LEVEL_ID + " integer)";
    private static final String DATABASE_CREATE_DOWNLOAD_TABLE = "create table if not exists "
            + DATABASE_DOWNLOAD_TABLE + "("
            + "id integer primary key autoincrement,"
            + KEY_LEVEL_ID + " integer , "
            + KEY_SUB_LEVEL_ID + " integer, "
            + KEY_IS_DOWNLOAD + " integer, "
            + KEY_MODEL_ID + " integer  , "
            + KEY_URL + " text)";
    private static final String CREATE_IS_SAVE_POINT_TABLE = "create table if not exists "
            + DATABASE_IS_SAVE_POINT_TABLE + "("
            + KEY_LOCK_ID + " integer primary key autoincrement, "
            + KEY_IS_SAVE_POINT + " integer, "
            + KEY_LEVEL_ID + " integer, "
            + KEY_SUB_LEVEL_ID + " integer)";
    private static DatabaseHelper instance;
    private static SQLiteDatabase db;
    private final String TAG = getClass().getSimpleName();
    private final String PASS = Utils.databasePassKey("nayan5565@gmail.com", "As us");
    private Context context;

    public DatabaseHelper(Context context) {

        this.context = context;
        instance = this;
        openDB(context);
        onCreate();
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    private void openDB(Context context) {
        SQLiteDatabase.loadLibs(context);
        File databaseFile = context.getDatabasePath(DATABASE_NAME);
        if (!databaseFile.exists()) {
            databaseFile.mkdirs();
            databaseFile.delete();
        }
        if (db == null)
            db = SQLiteDatabase.openOrCreateDatabase(databaseFile, PASS, null);
    }


    public void onCreate() {
        db.execSQL(DATABASE_CREATE_LEVEL_TABLE);
        db.execSQL(DATABASE_CREATE_ALL_CONTENTS_TABLE);
        db.execSQL(DATABASE_CREATE_SUB_LEVEL_TABLE);
        db.execSQL(DATABASE_CREATE_LOCK_TABLE);
        db.execSQL(DATABASE_CREATE_ALL_WORDS_TABLE);
        db.execSQL(DATABASE_CREATE_COLOR_TABLE);
        db.execSQL(DATABASE_CREATE_DOWNLOAD_TABLE);
        db.execSQL(CREATE_IS_SAVE_POINT_TABLE);

    }

    public void addLevelFromJson(MLevel mLevel) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, mLevel.getName());
            values.put(KEY_LEVEL_ID, mLevel.getLid());
            values.put(KEY_UPDATE_DATE, mLevel.getUpdate_date());
            values.put(KEY_HOW_TO, mLevel.getHowto());
//            values.put(KEY_DIFFICULTY, mLevel.getDifficulty());
            values.put(KEY_TOTAL_S_LEVEL, mLevel.getTotal_slevel());
//            if (mLevel.getBestpoint() > 0) {
//                values.put(KEY_POINT, mLevel.getBestpoint());
//            }

            if (mLevel.getLevelWinCount() > 0) {
                values.put(KEY_LEVEL_WIN_COUNT, mLevel.getLevelWinCount());
            }

            String sql = "select * from " + DATABASE_LEVEL_TABLE + " where " + KEY_LEVEL_ID + "='" + mLevel.getLid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                int update = db.update(DATABASE_LEVEL_TABLE, values, KEY_LEVEL_ID + "=?", new String[]{mLevel.getLid() + ""});
                Log.e("level", "update : " + update);
            } else {
                long v = db.insert(DATABASE_LEVEL_TABLE, null, values);
                Log.e("level", "insert : " + mLevel.getLid());

            }


        } catch (Exception e) {

        }
        if (cursor != null)
            cursor.close();
    }

    public void addSubFromJsom(MSubLevel mSubLevel) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_SUB_LEVEL_ID, mSubLevel.getLid());
            values.put(KEY_PARENT_ID, mSubLevel.getParentId());
            values.put(KEY_UNLOCK, mSubLevel.getUnlockNextLevel());
            values.put(KEY_CONTENT, mSubLevel.getContent());
            values.put(KEY_COLOR, mSubLevel.getColor());
            values.put(KEY_POINT, mSubLevel.getBestPoint());
            values.put(KEY_PARENT_NAME, mSubLevel.getParentName());
            values.put(KEY_NAME, mSubLevel.getName());
            values.put(KEY_HOW_TO, mSubLevel.getHowto());
//            values.put(KEY_POPUP, mSubLevel.getIsPopUp());
            values.put(KEY_COINS_PRICE, mSubLevel.getCoins_price());
            values.put(KEY_NO_OF_COINS, mSubLevel.getNo_of_coins());
            values.put(KEY_LOGIC, mSubLevel.getLogic());
//            values.put(KEY_DOWNLOAD, mSubLevel.getIsDownload());

            String sql = "select * from " + DATABASE_SUB_LEVEL_TABLE + " where " + KEY_SUB_LEVEL_ID + "='" + mSubLevel.getLid() + "'";
            cursor = db.rawQuery(sql, null);
            Log.e("cu", "has" + cursor);
            if (cursor != null && cursor.getCount() > 0) {
                int update = db.update(DATABASE_SUB_LEVEL_TABLE, values, KEY_SUB_LEVEL_ID + "=?", new String[]{mSubLevel.getLid() + ""});
                Log.e("sublevel", "sub level update : " + update);
            } else {
                long v = db.insert(DATABASE_SUB_LEVEL_TABLE, null, values);
                Log.e("sublevel", "sub level insert : " + v);

            }


        } catch (Exception e) {

        }
        if (cursor != null)
            cursor.close();
    }

    public void isPointSave(MData mData) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LEVEL_ID, mData.getLevelId());
            values.put(KEY_SUB_LEVEL_ID, mData.getSubLevelId());

            values.put(KEY_IS_SAVE_POINT, mData.getIsSavePoint());


            String sql = "select * from " + DATABASE_IS_SAVE_POINT_TABLE + " where " + KEY_LEVEL_ID + "='" + mData.getLevelId()
                    + "' AND " + KEY_SUB_LEVEL_ID + "='" + mData.getSubLevelId() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                int update = db.update(DATABASE_IS_SAVE_POINT_TABLE, values, KEY_LEVEL_ID + "=? AND " + KEY_SUB_LEVEL_ID + "=?", new String[]{mData.getLevelId() + "", mData.getSubLevelId() + ""});
                Log.e("DB", "isPoint upd :" + update);
            } else {
                long v = db.insert(DATABASE_IS_SAVE_POINT_TABLE, null, values);
                Log.e("DB", "isPoint insert:" + v);

            }


        } catch (Exception e) {
            Log.e("ERR", "mlock:" + e.toString());
        }

        if (cursor != null)
            cursor.close();
    }

    public MData getIsSavePoint(int levelId, int subLevelId) {
        ArrayList<MData> unlocks = new ArrayList<>();
        MData mData = new MData();
        String sql = "select * from " + DATABASE_IS_SAVE_POINT_TABLE + " where " + KEY_LEVEL_ID + "='" + levelId + "' "
                + " AND " + KEY_SUB_LEVEL_ID + "='" + subLevelId + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mData = new MData();
                mData.setId(cursor.getInt(cursor.getColumnIndex(KEY_LOCK_ID)));
                mData.setLevelId(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mData.setSubLevelId(cursor.getInt(cursor.getColumnIndex(KEY_SUB_LEVEL_ID)));
                mData.setIsSavePoint(cursor.getInt(cursor.getColumnIndex(KEY_IS_SAVE_POINT)));
                Log.e("unlock", "lock size" + unlocks.size());
                unlocks.add(mData);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return mData;
    }


    public String getPopUp(int levelId, int subLevelId) {
        String sql = "select * from " + DATABASE_SUB_LEVEL_TABLE + " where " + KEY_PARENT_ID + "='" + levelId + "'" + " AND " + KEY_SUB_LEVEL_ID + "='" + subLevelId + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {


                return cursor.getString(cursor.getColumnIndex(KEY_HOW_TO));


            } while (cursor.moveToNext());

        }
        cursor.close();


        return "";
    }

    public String getPopUpForLevel(int levelId) {
        String sql = "select * from " + DATABASE_LEVEL_TABLE + " where " + KEY_LEVEL_ID + "='" + levelId + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {


                return cursor.getString(cursor.getColumnIndex(KEY_HOW_TO));


            } while (cursor.moveToNext());

        }
        cursor.close();


        return "";
    }


    public void addDownloadData(MDownload mDownload) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LEVEL_ID, mDownload.getLevelId());
            values.put(KEY_SUB_LEVEL_ID, mDownload.getSubLevelId());
            values.put(KEY_IS_DOWNLOAD, mDownload.getIsDownload());
            values.put(KEY_MODEL_ID, mDownload.getId());
            values.put(KEY_URL, mDownload.getUrl());

            String sql = "select * from " + DATABASE_DOWNLOAD_TABLE + " where " + KEY_URL + "='" + mDownload.getUrl() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                int update = db.update(DATABASE_DOWNLOAD_TABLE, values, KEY_URL + "=?", new String[]{mDownload.getUrl() + ""});
                Log.e("downlaod", "content update : " + update);
            } else {
                long v = db.insert(DATABASE_DOWNLOAD_TABLE, null, values);
                Log.e("downlaod", "content urlName : " + mDownload.getUrl());

            }


        } catch (Exception e) {

        }

        if (cursor != null)
            cursor.close();
    }


    public void addLockData(MLock mLock) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LEVEL_ID, mLock.getLevel_id());
            values.put(KEY_SUB_LEVEL_ID, mLock.getSub_level_id());
            values.put(KEY_POINT, mLock.getBestPoint());
            values.put(KEY_POPUP, mLock.getPopup());
            values.put(KEY_COLOR, mLock.getColor());
            values.put(KEY_IS_SAVE_POINT, mLock.getIsSavePoint());
            values.put(KEY_TOTAL_POINT, mLock.getTotal_pont());
            values.put(KEY_UNLOCK, mLock.getUnlockNextLevel());

            String sql = "select * from " + DATABASE_LOCK_TABLE + " where " + KEY_LEVEL_ID + "='" + mLock.getLevel_id()
                    + "' AND " + KEY_SUB_LEVEL_ID + "='" + mLock.getSub_level_id() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                int update = db.update(DATABASE_LOCK_TABLE, values, KEY_LEVEL_ID + "=? AND " + KEY_SUB_LEVEL_ID + "=?", new String[]{mLock.getLevel_id() + "", mLock.getSub_level_id() + ""});
                Log.e("DB", "mlockUpdae:" + update);
            } else {
                long v = db.insert(DATABASE_LOCK_TABLE, null, values);
                Log.e("DB", "mlockAdded:" + v);

            }


        } catch (Exception e) {
            Log.e("ERR", "mlock:" + e.toString());
        }

        if (cursor != null)
            cursor.close();
    }


    public ArrayList<MLevel> getLevelData(int id) {
        ArrayList<MLevel> levelArrayList = new ArrayList<>();

        MLevel mLevel;
        String sql = "select * from " + DATABASE_LEVEL_TABLE + " where " + KEY_LEVEL_ID + "='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("cursor", "count :" + cursor.getCount());
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Log.e("do", "start");
                mLevel = new MLevel();
                mLevel.setLid(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mLevel.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                mLevel.setUpdate_date(cursor.getString(cursor.getColumnIndex(KEY_UPDATE_DATE)));
                mLevel.setTotal_slevel(cursor.getString(cursor.getColumnIndex(KEY_TOTAL_S_LEVEL)));
                mLevel.setHowto(cursor.getString(cursor.getColumnIndex(KEY_HOW_TO)));
                mLevel.setLevelWinCount(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_WIN_COUNT)));
                levelArrayList.add(mLevel);
                Log.e("do", "end");
            } while (cursor.moveToNext());


        }
        cursor.close();


        return levelArrayList;
    }

    public ArrayList<MLevel> getLevelAllData() {
        ArrayList<MLevel> levelArrayList = new ArrayList<>();

        MLevel mLevel;
        String sql = "select * from " + DATABASE_LEVEL_TABLE;
        Cursor cursor = db.rawQuery(sql, null);
        Log.e("cursor", "count :" + cursor.getCount());
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Log.e("do", "start");
                mLevel = new MLevel();
                mLevel.setLid(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mLevel.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
//                mLevel.setUpdate_date(cursor.getString(cursor.getColumnIndex(KEY_UPDATE_DATE)));
                mLevel.setTotal_slevel(cursor.getString(cursor.getColumnIndex(KEY_TOTAL_S_LEVEL)));
//                mLevel.setHowto(cursor.getString(cursor.getColumnIndex(KEY_HOW_TO)));
//                mLevel.setLevelWinCount(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_WIN_COUNT)));
                levelArrayList.add(mLevel);
                Log.e("do", "end");
            } while (cursor.moveToNext());


        }
        cursor.close();


        return levelArrayList;
    }

    public MLock getLocalData(int levelId, int subLevelId) {
        ArrayList<MLock> unlocks = new ArrayList<>();
        MLock mLock = new MLock();
        String sql = "select * from " + DATABASE_LOCK_TABLE + " where " + KEY_LEVEL_ID + "='" + levelId + "' "
                + " AND " + KEY_SUB_LEVEL_ID + "='" + subLevelId + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mLock = new MLock();
                mLock.setId(cursor.getInt(cursor.getColumnIndex(KEY_LOCK_ID)));
                mLock.setLevel_id(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mLock.setColor(cursor.getInt(cursor.getColumnIndex(KEY_COLOR)));
                mLock.setSub_level_id(cursor.getInt(cursor.getColumnIndex(KEY_SUB_LEVEL_ID)));
                mLock.setUnlockNextLevel(cursor.getInt(cursor.getColumnIndex(KEY_UNLOCK)));
                mLock.setPopup(cursor.getInt(cursor.getColumnIndex(KEY_POPUP)));
                mLock.setIsSavePoint(cursor.getInt(cursor.getColumnIndex(KEY_IS_SAVE_POINT)));
                mLock.setBestPoint(cursor.getInt(cursor.getColumnIndex(KEY_POINT)));
                mLock.setTotal_pont(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_POINT)));
                Log.e("totalPoint", "is :" + mLock.getTotal_pont());
                Log.e("unlock", "lock size" + unlocks.size());
                unlocks.add(mLock);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return mLock;
    }


    public int getLockTotalPointData(int id) {
        String sql = "select sum(" + KEY_TOTAL_POINT + ") as q  from " + DATABASE_LOCK_TABLE + " where " + KEY_LEVEL_ID + "='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            Log.e("DB", "data have");

//                mLock.setUnlockNextLevel(cursor.getInt(cursor.getColumnIndex(KEY_UNLOCK)));
//                mLock.setBestPoint(cursor.getInt(cursor.getColumnIndex(KEY_POINT)));
            return cursor.getInt(cursor.getColumnIndex("q"));

        } else {
            Log.e("DB", "data null");
        }
        cursor.close();


        return 0;
    }


    public ArrayList<MDownload> getDownloadData(int levelId, int isDownload) {
        ArrayList<MDownload> mDownloads = new ArrayList<>();
        MDownload mDownload = new MDownload();
        String sql = "select * from " + DATABASE_DOWNLOAD_TABLE + " where " + KEY_LEVEL_ID + "='" + levelId + "'" + " AND " + KEY_IS_DOWNLOAD + "='" + isDownload + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mDownload = new MDownload();
                mDownload.setLevelId(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mDownload.setSubLevelId(cursor.getInt(cursor.getColumnIndex(KEY_SUB_LEVEL_ID)));
                mDownload.setIsDownload(cursor.getInt(cursor.getColumnIndex(KEY_IS_DOWNLOAD)));
                mDownload.setUrl(cursor.getString(cursor.getColumnIndex(KEY_URL)));

                mDownloads.add(mDownload);

            } while (cursor.moveToNext());

        }
        cursor.close();


        return mDownloads;
    }

    public ArrayList<MSubLevel> getSubLevelData(int id) {
        ArrayList<MSubLevel> assetArrayList = new ArrayList<>();
        Log.e("DB", "S1");
        MSubLevel mSubLevel;
        String sql = "select a.s_lid,a.pNm,a.content,a.how_to,a.k_Logic,a.pid,a.name,a.coins_price,a.no_of_coins,b.un_lock,b.color_c,b.best_point from sub a left join lock_tb b on a.pid=b.lid AND a.s_lid=b.s_lid where a." + KEY_PARENT_ID + "='" + id + "'";
//                " from " + DATABASE_SUB_LEVEL_TABLE + " a where " + KEY_PARENT_ID + "='" + id + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            Log.e("DB", "S2 :" + cursor.getCount());
            do {
                mSubLevel = new MSubLevel();
                mSubLevel.setLid(cursor.getInt(cursor.getColumnIndex(KEY_SUB_LEVEL_ID)));
                mSubLevel.setColor(cursor.getInt(cursor.getColumnIndex(KEY_COLOR)));
                mSubLevel.setLogic(cursor.getInt(cursor.getColumnIndex(KEY_LOGIC)));
                mSubLevel.setContent(cursor.getInt(cursor.getColumnIndex(KEY_CONTENT)));
                mSubLevel.setUnlockNextLevel(cursor.getInt(cursor.getColumnIndex(KEY_UNLOCK)));
                mSubLevel.setHowto(cursor.getString(cursor.getColumnIndex(KEY_HOW_TO)));

                try {
                    mSubLevel.setBestPoint(cursor.getInt(cursor.getColumnIndex(KEY_POINT)));
                } catch (Exception e) {
                    mSubLevel.setBestPoint(0);
                }

                mSubLevel.setParentId(cursor.getInt(cursor.getColumnIndex(KEY_PARENT_ID)));
                mSubLevel.setParentName(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NAME)));
                mSubLevel.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                mSubLevel.setCoins_price(cursor.getString(cursor.getColumnIndex(KEY_COINS_PRICE)));
                mSubLevel.setNo_of_coins(cursor.getString(cursor.getColumnIndex(KEY_NO_OF_COINS)));
                assetArrayList.add(mSubLevel);
                Log.e("DB", "S3 :" + mSubLevel.getUnlockNextLevel());

            } while (cursor.moveToNext());

        }
        cursor.close();


        return assetArrayList;
    }


    public void addAllContentsData(MAllContent mAllContent) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_CONTENT, mAllContent.getContent());
            values.put(KEY_MODEL_ID, mAllContent.getMid());
            values.put(KEY_LEVEL, mAllContent.getLevel());
            values.put(KEY_IMAGE, mAllContent.getImg());
            values.put(KEY_PARENT_ID, mAllContent.getParentLevelId());
            values.put(KEY_SUB_LEVEL_ID, mAllContent.getSublevelId());
            values.put(KEY_TEXT, mAllContent.getTxt());
            values.put(KEY_SOUNDS, mAllContent.getAud());
            values.put(KEY_PRESENT_TYPE, mAllContent.getPresentType());
            values.put(KEY_VIDEO, mAllContent.getVid());
            values.put(KEY_LOGIC, mAllContent.getLogic());
            values.put(KEY_SEN, mAllContent.getSen());
            values.put(KEY_PRESENT_ID, mAllContent.getPresentId());

            String sql = "select * from " + DATABASE_ALL_CONTENTS_TABLE + " where " + KEY_MODEL_ID + "='" + mAllContent.getMid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                int update = db.update(DATABASE_ALL_CONTENTS_TABLE, values, KEY_MODEL_ID + "=?", new String[]{mAllContent.getMid() + ""});
                Log.e("allContents", " update : " + mAllContent.getMid());
            } else {
                long v = db.insert(DATABASE_ALL_CONTENTS_TABLE, null, values);
                Log.e("allContents", " insert text: " + mAllContent.getTxt());
                Log.e("allContents", " insert lid: " + mAllContent.getContent());
                Log.e("allContents", " insert mid: " + mAllContent.getMid());

            }


        } catch (Exception e) {

        }

        if (cursor != null)
            cursor.close();
    }

    public ArrayList<MAllContent> getAllContentsData(int levelId, int content) {
        ArrayList<MAllContent> mAllContents = new ArrayList<>();
        MAllContent mAllContent = new MAllContent();
        String sql = "select * from " + DATABASE_ALL_CONTENTS_TABLE + " where " + KEY_LEVEL + "='" + levelId + "'" + " AND " + KEY_CONTENT + "='" + content + "'";
//        String sql = "select * from " + DATABASE_ALL_CONTENTS_TABLE + " where " + KEY_PARENT_ID + "='" + levelId + "'" + " AND " + KEY_SUB_LEVEL_ID + "='" + subLevelId + "'" + " AND " + KEY_LOGIC + "='" + logic + "'" + " AND " + KEY_LEVEL_ID + "='" + contentsId + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mAllContent = new MAllContent();
                mAllContent.setParentLevelId(cursor.getInt(cursor.getColumnIndex(KEY_PARENT_ID)));
                mAllContent.setSublevelId(cursor.getInt(cursor.getColumnIndex(KEY_SUB_LEVEL_ID)));
                mAllContent.setLogic(cursor.getInt(cursor.getColumnIndex(KEY_LOGIC)));
                mAllContent.setContent(cursor.getInt(cursor.getColumnIndex(KEY_CONTENT)));
                mAllContent.setLevel(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL)));
                mAllContent.setMid(cursor.getInt(cursor.getColumnIndex(KEY_MODEL_ID)));
                mAllContent.setAud(cursor.getString(cursor.getColumnIndex(KEY_SOUNDS)));
                mAllContent.setVid(cursor.getString(cursor.getColumnIndex(KEY_VIDEO)));
                mAllContent.setTxt(cursor.getString(cursor.getColumnIndex(KEY_TEXT)));
                mAllContent.setImg(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                mAllContent.setSen(cursor.getString(cursor.getColumnIndex(KEY_SEN)));
                mAllContent.setPresentType(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_TYPE)));
                mAllContent.setPresentId(cursor.getInt(cursor.getColumnIndex(KEY_PRESENT_ID)));

                mAllContents.add(mAllContent);

            } while (cursor.moveToNext());

        }
        cursor.close();


        return mAllContents;
    }

    public void addAllWordsData(MWords mWords) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_WORDS_ID, mWords.getWid());
            values.put(KEY_WORDS_CONTENTS_ID, mWords.getContentId());
            values.put(KEY_WORDS_IMG, mWords.getWimg());
            values.put(KEY_WORDS_LETTER, mWords.getWletter());
            values.put(KEY_WORDS_Word, mWords.getWword());
            values.put(KEY_WORDS_SOUND, mWords.getWsound());


            String sql = "select * from " + DATABASE_ALL_WORDS_TABLE + " where " + KEY_WORDS_ID + "='" + mWords.getWid() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                int update = db.update(DATABASE_ALL_WORDS_TABLE, values, KEY_WORDS_ID + "=?", new String[]{mWords.getWid() + ""});
                Log.e("allWords", " update : " + update);
            } else {
                long v = db.insert(DATABASE_ALL_WORDS_TABLE, null, values);
                Log.e("allWords", " insert : " + v);

            }


        } catch (Exception e) {

        }
        if (cursor != null)
            cursor.close();
    }

    public void addcolor(MColor mColor) {
        Cursor cursor = null;
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_LEVEL_ID, mColor.getLevelId());
            values.put(KEY_SUB_LEVEL_ID, mColor.getSubLevelId());
            values.put(KEY_COLOR, mColor.getColor());

            String sql = "select * from " + DATABASE_COLOR_TABLE + " where " + KEY_LEVEL_ID + "='" + mColor.getLevelId()
                    + "' AND " + KEY_SUB_LEVEL_ID + "='" + mColor.getSubLevelId() + "'";
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.getCount() > 0) {
                int update = db.update(DATABASE_COLOR_TABLE, values, KEY_LEVEL_ID + "=? AND " + KEY_SUB_LEVEL_ID + "=?", new String[]{mColor.getLevelId() + "", mColor.getSubLevelId() + ""});
                Log.e("DB", "color:" + update);
            } else {
                long v = db.insert(DATABASE_COLOR_TABLE, null, values);
                Log.e("DB", "color:" + v);

            }


        } catch (Exception e) {
            Log.e("ERR", "color:" + e.toString());
        }

        if (cursor != null)
            cursor.close();
    }

    public MColor getColor(int levelId, int subLevelId) {
        ArrayList<MColor> mColors = new ArrayList<>();
        MColor mColor = new MColor();
        String sql = "select * from " + DATABASE_COLOR_TABLE + " where " + KEY_LEVEL_ID + "='" + levelId + "' "
                + " AND " + KEY_SUB_LEVEL_ID + "='" + subLevelId + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mColor = new MColor();
                mColor.setId(cursor.getInt(cursor.getColumnIndex(KEY_WORDS_ID)));
                mColor.setLevelId(cursor.getInt(cursor.getColumnIndex(KEY_LEVEL_ID)));
                mColor.setSubLevelId(cursor.getInt(cursor.getColumnIndex(KEY_SUB_LEVEL_ID)));
                mColor.setColor(cursor.getInt(cursor.getColumnIndex(KEY_COLOR)));
                Log.e("totalPoint", "is :" + mColor.getColor());
                Log.e("unlock", "lock size" + mColors.size());
                mColors.add(mColor);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return mColor;
    }

    public ArrayList<MWords> getAllWordsData(int id) {
        ArrayList<MWords> assetArrayList = new ArrayList<>();

        MWords mWords;
        String sql = "select * from " + DATABASE_ALL_WORDS_TABLE + " where " + KEY_WORDS_CONTENTS_ID + "='" + id + "'";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mWords = new MWords();
                mWords.setWid(cursor.getInt(cursor.getColumnIndex(KEY_WORDS_ID)));
                mWords.setWsound(cursor.getString(cursor.getColumnIndex(KEY_WORDS_SOUND)));
                mWords.setWword(cursor.getString(cursor.getColumnIndex(KEY_WORDS_Word)));
                mWords.setContentId(cursor.getInt(cursor.getColumnIndex(KEY_WORDS_CONTENTS_ID)));
                mWords.setWimg(cursor.getString(cursor.getColumnIndex(KEY_WORDS_IMG)));
                mWords.setWletter(cursor.getString(cursor.getColumnIndex(KEY_WORDS_LETTER)));
                assetArrayList.add(mWords);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return assetArrayList;
    }

    public ArrayList<String> getContentUrl(int levelId, int content) {
        ArrayList<String> cUrls = new ArrayList<>();

        String sql = "select * from " + DATABASE_ALL_CONTENTS_TABLE + " where " + KEY_LEVEL + "='" + levelId + "'" + " AND " + KEY_CONTENT + "='" + content + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                cursor.getInt(cursor.getColumnIndex(KEY_LEVEL));
                cursor.getInt(cursor.getColumnIndex(KEY_CONTENT));
                cUrls.add(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));


            } while (cursor.moveToNext());

        }
        cursor.close();
        return cUrls;
    }

    public ArrayList<String> getWordsUrl(int contentId) {
        ArrayList<String> wUrls = new ArrayList<>();

        String sql = "select * from " + DATABASE_ALL_WORDS_TABLE + " where " + KEY_WORDS_CONTENTS_ID + "='" + contentId + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                cursor.getInt(cursor.getColumnIndex(KEY_WORDS_CONTENTS_ID));
                wUrls.add(cursor.getString(cursor.getColumnIndex(KEY_WORDS_IMG)));


            } while (cursor.moveToNext());

        }
        cursor.close();
        return wUrls;
    }

    public ArrayList<Integer> getContent(int parentId) {
        ArrayList<Integer> conten = new ArrayList<>();

        String sql = "select * from " + DATABASE_SUB_LEVEL_TABLE + " where " + KEY_PARENT_ID + "='" + parentId + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                cursor.getInt(cursor.getColumnIndex(KEY_PARENT_ID));
                conten.add(cursor.getInt(cursor.getColumnIndex(KEY_CONTENT)));


            } while (cursor.moveToNext());

        }
        cursor.close();
        return conten;
    }

    public ArrayList<Integer> getContentsId(int levelId, int content) {
        ArrayList<Integer> contenId = new ArrayList<>();

        String sql = "select * from " + DATABASE_ALL_CONTENTS_TABLE + " where " + KEY_LEVEL + "='" + levelId + "'" + " AND " + KEY_CONTENT + "='" + content + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                cursor.getInt(cursor.getColumnIndex(KEY_LEVEL));
                cursor.getInt(cursor.getColumnIndex(KEY_CONTENT));
                contenId.add(cursor.getInt(cursor.getColumnIndex(KEY_MODEL_ID)));


            } while (cursor.moveToNext());

        }
        cursor.close();
        return contenId;
    }

    private void close() {
        if (db != null && db.isOpen()) {
            db.close();
            db = null;
        }
    }
}
