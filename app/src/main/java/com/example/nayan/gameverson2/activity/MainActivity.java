package com.example.nayan.gameverson2.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MColor;
import com.example.nayan.gameverson2.model.MDownload;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MPost;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.model.MWords;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
import com.example.nayan.gameverson2.tools.DialogSoundOnOff;
import com.example.nayan.gameverson2.tools.FilesDownload;
import com.example.nayan.gameverson2.tools.Global;
import com.example.nayan.gameverson2.tools.MyApplication;
import com.example.nayan.gameverson2.tools.MyGoogleAnalytics;
import com.example.nayan.gameverson2.tools.Utils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static File externalFilesDir = MyApplication.getMyInstance().getAppContext().getExternalFilesDir(null);
    public static final String PARENT = externalFilesDir.getAbsolutePath() + File.separator + "MatchGame";
    public static String image = PARENT + File.separator + "AllImage";
    public static String bothImg = PARENT + File.separator + "AllImage";
    public static String bothSnd = PARENT + File.separator + "AllSound";

    public static String sounds = PARENT + File.separator + "AllSound";
    private static MainActivity mainActivity;

    private MPost mPost = new MPost();
    private MDownload mDownload;
    private ArrayList<MDownload> mDownloads;
    private ArrayList<MDownload> banmDownloads;
    private Handler handler;
    private ArrayList<MDownload> ongkomDownloads;
    private ArrayList<MDownload> mathmDownloads;
    private Dialog dialog1;
    public ArrayList<Integer> conten = new ArrayList<>();
    public ArrayList<Integer> contenIds = new ArrayList<>();
    public ArrayList<String> cUrls = new ArrayList<>();
    public ArrayList<String> allWUrls = new ArrayList<>();
    public ArrayList<String> wUrls = new ArrayList<>();
    public ArrayList<Integer> allContentId = new ArrayList<>();
    public ArrayList<String> cAllUrls = new ArrayList<>();
    public ArrayList<String> uniquesUrls = new ArrayList<>();
    private MColor mColor;
    private ArrayList<MColor> mColors;
    private ArrayList<MLevel> levelsDatas;
    private String B_URL = Global.BASE_URL;
    private Button btnSetting, btnResult;
    private ImageView cloud1, cloud2, btnBangla, btnEnglish, btnMath, btnBanglaMath, btnDrawing;
    private MLevel mLevel;
    private DatabaseHelper database;
    private TextView txtSub, txtMath, txtDrawing, txtEnglish, txtBanglaMath, textName, txtEnglisg, txtMatht;
    private String image1;
    private int STORAGE_PERMISSION_CODE = 23;
    private Gson gson = new Gson();

    public static MainActivity getInstance() {
        return mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        MyGoogleAnalytics.getInstance().setupAnalytics("Main Activity");
        requestStoragePermissionToMashmallow();
//        mPost.setSubLevel(database.getSubLevelData(1));
        mPost.setDeviceId(Utils.getDeviceId(this));
        mPost.setUserEmail(Utils.getPhoneGmailAcc(this));
        Utils.postDataFromDatabase(mPost);
        Utils.logIn(mPost.getUserEmail(), "123456", mPost.getDeviceId());
        syncApi();
//        getOnlineData();

        getLocalData();


    }

    public void banglaImage(int start) {
        cAllUrls.clear();
        conten = database.getContent(1);

        int max = start + 6;
        if (max > conten.size()) {
            max = conten.size();
        }

        int maxContent = conten.get(max - 1);
//        Utils.savePref("1", maxContent + "");
//        DialogSoundOnOff.savePref(MainActivity.this, "1", max + "");
        Global.maximumContentOfBangla = maxContent;
        Global.startDownBan = max;

        for (int i = start; i < max; i++) {

            cUrls = database.getContentUrl(1, conten.get(i));

            cAllUrls.addAll(cUrls);
            contenIds = database.getContentsId(1, conten.get(i));
            allContentId.addAll(contenIds);

        }

        for (int i = 0; i < cAllUrls.size(); i++) {
            if (!uniquesUrls.contains(cAllUrls.get(i))) {
                uniquesUrls.add(cAllUrls.get(i));
                for (int j = 0; j < allContentId.size(); j++) {
                    wUrls = database.getWordsUrl(allContentId.get(j));
                    allWUrls.addAll(wUrls);
                }
            }
        }

        for (int i = 0; i < allWUrls.size(); i++) {
            if (!uniquesUrls.contains(allWUrls.get(i))) {
                uniquesUrls.add(allWUrls.get(i));
            }
        }
        Global.URLS.addAll(uniquesUrls);
        Utils.log("Urls", "cont unique size " + uniquesUrls.size());
        Utils.log("Urls", "cont all size " + cAllUrls.size());
        Utils.log("Urls", "cont size " + cUrls.size());
        Utils.log("Urls", "content id " + allContentId.size());
        Utils.log("Urls", "word size " + allWUrls.size());
        Utils.log("Urls", "content " + conten.size());
    }

    public void allCatagoryImage(int start, int level, Context context) {
        cAllUrls.clear();
        conten = database.getContent(level);

        int max = start + 6;
        if (max > conten.size()) {
            max = conten.size();
        }

        int maxContent = conten.get(max - 1);
        Utils.savePref(level + "", maxContent + "");
        DialogSoundOnOff.savePref(level + "", max + "");


        for (int i = start; i < max; i++) {
            cUrls = database.getContentUrl(level, conten.get(i));

            cAllUrls.addAll(cUrls);
            contenIds = database.getContentsId(level, conten.get(i));
            allContentId.addAll(contenIds);

        }

        for (int i = 0; i < cAllUrls.size(); i++) {
            if (!uniquesUrls.contains(cAllUrls.get(i))) {
                uniquesUrls.add(cAllUrls.get(i));
                for (int j = 0; j < allContentId.size(); j++) {
                    wUrls = database.getWordsUrl(allContentId.get(j));
                    allWUrls.addAll(wUrls);
                }
            }
        }

        for (int i = 0; i < allWUrls.size(); i++) {
            if (!uniquesUrls.contains(allWUrls.get(i))) {
                uniquesUrls.add(allWUrls.get(i));
            }
        }
        Global.URLS.addAll(uniquesUrls);
        Utils.log("Urls", "cont unique size " + uniquesUrls.size());
        Utils.log("Urls", "cont all size " + cAllUrls.size());
        Utils.log("Urls", "cont size " + cUrls.size());
        Utils.log("Urls", "content id " + allContentId.size());
        Utils.log("Urls", "word size " + allWUrls.size());
        Utils.log("Urls", "content " + conten.size());
    }

    public void ongkoImage(int start) {
        cAllUrls.clear();
        conten = database.getContent(2);

        int max = start + 6;
        if (conten.size() < max) {
            max = conten.size();
        }
        int maxContent = conten.get(max - 1);
//        Utils.savePref("2", maxContent + "");
//        DialogSoundOnOff.savePref(MainActivity.this, "2", max + "");
        Global.maximumContentOfOngko = maxContent;
        Global.startDownOngk = max;
        for (int i = start; i < max; i++) {
            cUrls = database.getContentUrl(2, conten.get(i));
            cAllUrls.addAll(cUrls);
            contenIds = database.getContentsId(2, conten.get(i));
            allContentId.addAll(contenIds);

        }

        for (int i = 0; i < cAllUrls.size(); i++) {
            if (!uniquesUrls.contains(cAllUrls.get(i))) {
                uniquesUrls.add(cAllUrls.get(i));

            }
        }

        for (int j = 0; j < allContentId.size(); j++) {
            wUrls = database.getWordsUrl(allContentId.get(j));
            allWUrls.addAll(wUrls);
        }

        for (int i = 0; i < allWUrls.size(); i++) {
            if (!uniquesUrls.contains(allWUrls.get(i))) {
                uniquesUrls.add(allWUrls.get(i));
            }
        }
        Global.URLS.addAll(uniquesUrls);
        Utils.log("Urls", "cont unique size " + uniquesUrls.size());
        Utils.log("Urls", "cont all size " + cAllUrls.size());
        Utils.log("Urls", "cont size " + cUrls.size());
        Utils.log("Urls", "content id " + allContentId.size());
        Utils.log("Urls", "word size " + allWUrls.size());
        Utils.log("Urls", "content " + conten.size());
    }

    public void englishImage(int start) {
        cAllUrls.clear();
        conten = database.getContent(3);

        int max = start + 6;
        if (conten.size() < max) {
            max = conten.size();
        }
        int maxContent = conten.get(max - 1);
        Global.maximumContentOfEnglish = maxContent;
//        Utils.savePref("3", maxContent + "");
//        DialogSoundOnOff.savePref(MainActivity.this, "3", max + "");
        Global.startDownEng = max;
        for (int i = start; i < max; i++) {
            cUrls = database.getContentUrl(3, conten.get(i));
            cAllUrls.addAll(cUrls);
            contenIds = database.getContentsId(3, conten.get(i));
            allContentId.addAll(contenIds);

        }

        for (int i = 0; i < cAllUrls.size(); i++) {
            if (!uniquesUrls.contains(cAllUrls.get(i))) {
                uniquesUrls.add(cAllUrls.get(i));

            }
        }

        for (int j = 0; j < allContentId.size(); j++) {
            wUrls = database.getWordsUrl(allContentId.get(j));
            allWUrls.addAll(wUrls);
        }

        for (int i = 0; i < allWUrls.size(); i++) {
            if (!uniquesUrls.contains(allWUrls.get(i))) {
                uniquesUrls.add(allWUrls.get(i));
            }
        }
        Global.URLS.addAll(uniquesUrls);
        Utils.log("Urls", "cont unique size " + uniquesUrls.size());
        Utils.log("Urls", "cont all size " + cAllUrls.size());
        Utils.log("Urls", "cont size " + cUrls.size());
        Utils.log("Urls", "content id " + allContentId.size());
        Utils.log("Urls", "word size " + allWUrls.size());
        Utils.log("Urls", "content " + conten.size());
    }

    public void mathImage(int start) {
        cAllUrls.clear();
        conten = database.getContent(4);

        int max = start + 6;
        if (conten.size() < max) {
            max = conten.size();
        }
        int maxContent = conten.get(max - 1);
        Global.maximumContentOfMath = maxContent;
//        Utils.savePref("4", maxContent + "");
//        DialogSoundOnOff.savePref(MainActivity.this, "4", max + "");
        Global.startDownMath = max;
        for (int i = start; i < max; i++) {
            cUrls = database.getContentUrl(4, conten.get(i));
            cAllUrls.addAll(cUrls);
            contenIds = database.getContentsId(4, conten.get(i));
            allContentId.addAll(contenIds);

        }

        for (int i = 0; i < cAllUrls.size(); i++) {
            if (!uniquesUrls.contains(cAllUrls.get(i))) {
                uniquesUrls.add(cAllUrls.get(i));

            }
        }

        for (int j = 0; j < allContentId.size(); j++) {
            wUrls = database.getWordsUrl(allContentId.get(j));
            allWUrls.addAll(wUrls);
        }

        for (int i = 0; i < allWUrls.size(); i++) {
            if (!uniquesUrls.contains(allWUrls.get(i))) {
                uniquesUrls.add(allWUrls.get(i));
            }
        }
        Global.URLS.addAll(uniquesUrls);
//        imageDownload();
        Utils.log("Urls", "cont unique size " + uniquesUrls.size());
        Utils.log("Urls", "cont all size " + cAllUrls.size());
        Utils.log("Urls", "cont size " + cUrls.size());
        Utils.log("Urls", "content id " + allContentId.size());
        Utils.log("Urls", "word size " + allWUrls.size());
        Utils.log("Urls", "content " + conten.size());
    }

    public void imageDownload() {
        if (!Utils.isInternetOn(this)) {
            Utils.log("Internet", "No Internet");
            return;
        }
        FilesDownload filesDownload = FilesDownload.getInstance(this, bothImg);
        for (int i = 0; i < uniquesUrls.size(); i++) {
            filesDownload.addUrl(Global.IMAGE_URL + uniquesUrls.get(i));

        }
        FilesDownload.getInstance(MainActivity.this, "").start();
    }

    private void init() {
        mColors = new ArrayList<>();
        Global.URLS = new ArrayList<>();
        handler = new Handler();
        mainActivity = this;
        Global.mDownloads = new ArrayList<MDownload>();
        mDownloads = new ArrayList<MDownload>();
        banmDownloads = new ArrayList<MDownload>();
        btnResult = (Button) findViewById(R.id.btnResult);
        btnResult.setOnClickListener(this);
        levelsDatas = new ArrayList<>();
        textName = (TextView) findViewById(R.id.txtGameNames);
        Utils.setFont(this, "carterone", textName);
        database = new DatabaseHelper(this);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(this);
        mLevel = new MLevel();
        btnBangla = (ImageView) findViewById(R.id.btnBangla);
        btnBangla.setOnClickListener(this);
        btnEnglish = (ImageView) findViewById(R.id.btnEnglish);
        btnEnglish.setOnClickListener(this);
        btnMath = (ImageView) findViewById(R.id.btnMath);
        btnMath.setOnClickListener(this);
        txtSub = (TextView) findViewById(R.id.txtSub);
        txtMath = (TextView) findViewById(R.id.txtMath);
        txtEnglish = (TextView) findViewById(R.id.txtEnglish);
        txtBanglaMath = (TextView) findViewById(R.id.txtBanglaMath);
        Global.levels = new ArrayList<>();
        btnBanglaMath = (ImageView) findViewById(R.id.btnBanglaMath);
        btnBanglaMath.setOnClickListener(this);
        cloud1 = (ImageView) findViewById(R.id.imgCloud1);
        cloud2 = (ImageView) findViewById(R.id.imgCloud2);
        cloud1.postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.getScreenSize(MainActivity.this);
                Utils.moveAnimation(cloud1, cloud2);
                Utils.move(cloud1, cloud2);
            }
        }, 100);
        image1 = DialogSoundOnOff.getPREF(DialogSoundOnOff.KEY_IMAGE);
        if (image1.equals(1 + "")) {
            Utils.isSoundPlay = true;

        } else if (image1.equals(0 + "")) {
            Utils.isSoundPlay = false;

        }


    }

    public void syncApi() {
        Log.e("Internet", "sync");
        if (!Utils.isInternetOn(this)) {
            Log.e("Internet", "No internet");
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://www.radhooni.com/content/match_game/v1/sync.php", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("Internet", response.toString());
                try {
                    String sync = DialogSoundOnOff.getPREF("sync");
                    Log.e("Internet","date "+sync);
                    if (!sync.equals(response.getString("update_date"))) {
                        getOnlineData();
                    }
                    DialogSoundOnOff.savePref("sync", response.getString("update_date"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void requestStoragePermissionToMashmallow() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                downloadAssets();
                //Displaying a toast
//                Utils.toastMassage(this, "Permission granted now you can read the storage");
            } else {
                //Displaying another toast if permission is not granted
                Utils.toastMassage(this, "Oops you just denied the permission");
            }
        }
    }


    private void getLocalData() {
        levelsDatas = database.getLevelAllData();
        Utils.log("levels ", "Alldata : " + levelsDatas.size());
        if (levelsDatas.size() == 0) {
            return;

        } else {
            txtSub.setText(levelsDatas.get(0).getTotal_slevel());
            txtBanglaMath.setText(levelsDatas.get(1).getTotal_slevel());
            txtEnglish.setText(levelsDatas.get(2).getTotal_slevel());
            txtMath.setText(levelsDatas.get(3).getTotal_slevel());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {

        }

        if (id == R.id.action_settings) {

            DialogSoundOnOff.dialogShow(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getOnlineData() {

        Log.e("sep", "1");
        if (!Utils.isInternetOn(this)) {
            Log.e("Internet", "No internet");
            getLocalData();
            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dia_internet_alert);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView txtInternet = (TextView) dialog.findViewById(R.id.txtInternet);
            txtInternet.setText(Global.internetAlert);
            dialog.show();

            return;
        }
        Log.e("sep", "11");
        dialog1 = new Dialog(this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.dia_loading);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.setCanceledOnTouchOutside(false);
        dialog1.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_LEVELS, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.e("sep", "2" + response.toString());
                        Global.levels = new ArrayList<MLevel>();
                        Global.mSubLevelArrayList = new ArrayList<MSubLevel>();

                        try {
                            MLevel[] levelData = gson.fromJson(response.getJSONObject("puzzle").getJSONArray("level").toString(), MLevel[].class);
                            Global.levels = new ArrayList<MLevel>(Arrays.asList(levelData));

                            Log.e("levelData", " is id " + Global.levels.size());

                            for (int i = 0; i < Global.levels.size(); i++) {

                                for (int j = 0; j < Global.levels.get(i).getSub().size(); j++) {

                                    MSubLevel subLevel = Global.levels.get(i).getSub().get(j);
                                    subLevel.setParentId(Global.levels.get(i).getLid());
                                    subLevel.setParentName(Global.levels.get(i).getName());
                                    Log.e("levelData", " is id " + Global.levels.get(i).getLid());

                                    Global.mSubLevelArrayList.add(subLevel);

                                    Log.e("sssss", " is pid " + subLevel.getParentId());
                                    Log.e("sssss", " is lid " + subLevel.getLid());
                                }

                            }

                            Log.e("subleve", "data" + Global.mSubLevelArrayList.size());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        saveLevelToDb();
                        saveSubLevelToDb();
                        getLocalData();
                        getEnglishContentData();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        getOnlineData();
                        Log.e("sep", "3");
                        getEnglishContentData();
                    }
                }
        );
    }

    private void getEnglishContentData() {
        if (!Utils.isInternetOn(this)) {

            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_ENGLISH, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.e("english", "2" + response.toString());

                        Global.English_words = new ArrayList<MWords>();

                        try {

                            MAllContent[] data = gson.fromJson(response.getJSONArray("contents").toString(), MAllContent[].class);
                            Global.English = new ArrayList<MAllContent>(Arrays.asList(data));
                            for (int i = 0; i < Global.English.size(); i++) {
//                                Utils.english.get(i).setPresentId(i+1);
                                mDownload = new MDownload();
                                mDownload.setLevelId(3);
                                mDownload.setSubLevelId(Global.English.get(i).getMid());
                                mDownload.setUrl(Global.English.get(i).getImg());
                                Global.mDownloads.add(mDownload);
//                                mDownload = new MDownload();
//                                mDownload.setLevelId(3);
//                                mDownload.setSubLevelId(Global.English.get(i).getMid());
//                                mDownload.setUrl(Global.English.get(i).getAud());
//                                Global.mDownloads.add(mDownload);
                                Global.English.get(i).setPresentType(i + 1);
                                for (int j = 0; j < Global.English.get(i).getWords().size(); j++) {
                                    MWords mWords = Global.English.get(i).getWords().get(j);
                                    mWords.setContentId(Global.English.get(i).getMid());
                                    Global.English_words.add(mWords);


                                    mDownload = new MDownload();
                                    mDownload.setLevelId(3);
                                    mDownload.setSubLevelId(Global.English.get(i).getMid());
                                    mDownload.setUrl(Global.English.get(i).getWords().get(j).getWimg());
                                    Global.mDownloads.add(mDownload);
//                                    mDownload = new MDownload();
//                                    mDownload.setLevelId(3);
//                                    mDownload.setSubLevelId(Global.English.get(i).getMid());
//                                    mDownload.setUrl(Global.English.get(i).getWords().get(j).getWsound());
//                                    Global.mDownloads.add(mDownload);

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("down", "add english   " + Global.mDownloads.size());
                        saveEnglishContentsOfAllLevelToDb();
                        saveEnglishWordsToDb();
//                        saveDownloadToDb();

                        getMathContentData();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
//                        B_URL = Global.ALTER_URL;
                        getEnglishContentData();
                        getMathContentData();
                    }
                }
        );
    }

    private void saveDownloadToDb() {
        for (MDownload mDownload : Global.mDownloads) {
            database.addDownloadData(mDownload);
        }
    }

    public void getDownload(int id, int isDown) {
        mDownloads = database.getDownloadData(id, isDown);
        Log.e("down", "is  " + mDownloads.size());
//        banmDownloads = new ArrayList<>();
//        banmDownloads = database.getDownloadData(1);

    }


    private void getBanglaContentData() {
        Utils.log("bangla", "step1");
        if (!Utils.isInternetOn(this)) {

            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_BANGLA, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        super.onSuccess(statusCode, headers, response);
                        Utils.log("bangla", "step2" + response.toString());


                        Global.BANGLA_words = new ArrayList<MWords>();

                        try {
                            Utils.log("bangla", "step3");
                            MAllContent[] data = gson.fromJson(response.getJSONArray("contents").toString(), MAllContent[].class);
                            Global.BANGLA = new ArrayList<MAllContent>(Arrays.asList(data));
                            Utils.log("bangla", "size" + Global.BANGLA.size());
                            for (int i = 0; i < Global.BANGLA.size(); i++) {
//                                Utils.english.get(i).setPresentId(i+1);
                                mDownload = new MDownload();
                                mDownload.setLevelId(1);
                                mDownload.setSubLevelId(Global.BANGLA.get(i).getMid());
                                mDownload.setUrl(Global.BANGLA.get(i).getImg());
                                Global.mDownloads.add(mDownload);
//                                mDownload = new MDownload();
//                                mDownload.setLevelId(1);
//                                mDownload.setSubLevelId(Global.BANGLA.get(i).getMid());
//                                mDownload.setUrl(Global.BANGLA.get(i).getAud());
//                                Global.mDownloads.add(mDownload);
                                Global.BANGLA.get(i).setPresentType(i + 1);
                                for (int j = 0; j < Global.BANGLA.get(i).getWords().size(); j++) {
                                    MWords mWords = Global.BANGLA.get(i).getWords().get(j);
                                    mWords.setContentId(Global.BANGLA.get(i).getMid());
                                    Global.BANGLA_words.add(mWords);
                                    mDownload = new MDownload();
                                    mDownload.setLevelId(1);
                                    mDownload.setSubLevelId(Global.BANGLA.get(i).getMid());
                                    mDownload.setUrl(Global.BANGLA.get(i).getWords().get(j).getWimg());
                                    Global.mDownloads.add(mDownload);
//                                    mDownload = new MDownload();
//                                    mDownload.setLevelId(1);
//                                    mDownload.setSubLevelId(Global.BANGLA.get(i).getMid());
//                                    mDownload.setUrl(Global.BANGLA_words.get(j).getWsound());
//                                    Global.mDownloads.add(mDownload);
                                }
                            }
                            Utils.log("bangla", "Wsize" + Global.BANGLA_words.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Utils.log("bangla", e.toString());
                        }
                        Utils.log("down", " add bangla " + Global.mDownloads.size());

                        saveBanglaContentsOfAllLevelToDb();
                        saveBanglaWordsToDb();
//                        saveDownloadToDb();


                        getBanglaMathContentData();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        Utils.log("bangla", "step5Errror");
                        getBanglaMathContentData();
                    }
                }
        );
    }

    private void getMathContentData() {
        if (!Utils.isInternetOn(this)) {

            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_MATH, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.e("math", "2" + response.toString());

                        Global.MATH_words = new ArrayList<MWords>();

                        try {
                            MAllContent[] data = gson.fromJson(response.getJSONArray("contents").toString(), MAllContent[].class);
                            Global.Maths = new ArrayList<MAllContent>(Arrays.asList(data));
                            for (int i = 0; i < Global.Maths.size(); i++) {
                                mDownload = new MDownload();
                                mDownload.setLevelId(4);
                                mDownload.setSubLevelId(Global.Maths.get(i).getMid());
                                mDownload.setUrl(Global.Maths.get(i).getImg());
                                Utils.log("url", "image " + mDownload.getUrl());
                                Global.mDownloads.add(mDownload);
//                                mDownload = new MDownload();
//                                mDownload.setLevelId(4);
//                                mDownload.setSubLevelId(Global.Maths.get(i).getMid());
//                                mDownload.setUrl(Global.Maths.get(i).getAud());
//                                Utils.log("url", "sound " + mDownload.getUrl());
//                                Global.mDownloads.add(mDownload);
                                Global.Maths.get(i).setPresentType(i + 1);
                                for (int j = 0; j < Global.Maths.get(i).getWords().size(); j++) {
                                    MWords mWords = Global.Maths.get(i).getWords().get(j);
                                    mWords.setContentId(Global.Maths.get(i).getMid());
                                    Global.MATH_words.add(mWords);
                                    mDownload = new MDownload();
                                    mDownload.setLevelId(4);
                                    mDownload.setSubLevelId(Global.Maths.get(i).getMid());
                                    mDownload.setUrl(Global.Maths.get(i).getWords().get(j).getWimg());
                                    Utils.log("url", "image w " + mDownload.getUrl());
                                    Global.mDownloads.add(mDownload);
//                                    mDownload = new MDownload();
//                                    mDownload.setLevelId(4);
//                                    mDownload.setLevelId(Global.Maths.get(i).getMid());
//                                    mDownload.setUrl(Global.Maths.get(i).getWords().get(j).getWsound());
//                                    Utils.log("url", "sound w " + mDownload.getUrl());
//                                    Global.mDownloads.add(mDownload);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("down", "add math   " + Global.mDownloads.size());
                        saveMathContentsOfAllLevelToDb();
                        saveMathWordsToDb();
//                        saveDownloadToDb();

                        getBanglaContentData();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        getMathContentData();
                        getBanglaContentData();
                    }
                }
        );
    }

    private void getBanglaMathContentData() {
        if (!Utils.isInternetOn(this)) {

            return;
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(B_URL + Global.API_BANGLA_MATH, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.e("ongko", "2" + response.toString());
                        dialog1.dismiss();
                        Global.BANGLA_MATH_words = new ArrayList<MWords>();

                        try {
                            MAllContent[] data = gson.fromJson(response.getJSONArray("contents").toString(), MAllContent[].class);
                            Global.BANGLA_Maths = new ArrayList<MAllContent>(Arrays.asList(data));
                            for (int i = 0; i < Global.BANGLA_Maths.size(); i++) {
                                mDownload = new MDownload();
                                mDownload.setLevelId(2);
                                mDownload.setSubLevelId(Global.BANGLA_Maths.get(i).getMid());
                                mDownload.setUrl(Global.BANGLA_Maths.get(i).getImg());
                                Global.mDownloads.add(mDownload);
                                mDownload = new MDownload();
//                                mDownload.setLevelId(2);
//                                mDownload.setSubLevelId(Global.BANGLA_Maths.get(i).getMid());
//                                mDownload.setUrl(Global.BANGLA_Maths.get(i).getAud());
//                                Global.mDownloads.add(mDownload);
                                Global.BANGLA_Maths.get(i).setPresentType(i + 1);
                                for (int j = 0; j < Global.BANGLA_Maths.get(i).getWords().size(); j++) {
                                    MWords mWords = Global.BANGLA_Maths.get(i).getWords().get(j);
                                    mWords.setContentId(Global.BANGLA_Maths.get(i).getMid());
                                    Global.BANGLA_MATH_words.add(mWords);
                                    mDownload = new MDownload();
                                    mDownload.setLevelId(2);
                                    mDownload.setSubLevelId(Global.BANGLA_Maths.get(i).getMid());
                                    mDownload.setUrl(Global.BANGLA_Maths.get(i).getWords().get(j).getWimg());
                                    Global.mDownloads.add(mDownload);
//                                    mDownload = new MDownload();
//                                    mDownload.setLevelId(2);
//                                    mDownload.setLevelId(Global.BANGLA_Maths.get(i).getMid());
//                                    mDownload.setUrl(Global.BANGLA_Maths.get(i).getWords().get(j).getWsound());
//                                    Global.mDownloads.add(mDownload);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("down", "add ongko   " + Global.mDownloads.size());
                        saveBanglaMathContentsOfAllLevelToDb();
                        saveBanglaMathWordsToDb();
//                        saveDownloadToDb();
//                        String start = DialogSoundOnOff.getPREF(MainActivity.this, 1 + "");
//                        int s = Integer.valueOf(start);
//                        if (s==0){
                        banglaImage(0);
                        ongkoImage(0);
                        englishImage(0);
                        mathImage(0);
                        imageDownload();
//                        }

                        int c = 0;
                        int d = 0;
//                        getDownload(1, 0);
//                        allImageDownload();
//                        getDownload(2, 0);
//                        allImageDownload();
//                        getDownload(3, 0);
//                        allImageDownload();
//                        getDownload(4, 0);
//                        allImageDownload();
//                        allImageDownload(0, 0);
//                        for (int i = 0; i < mDownloads.size(); i++) {
//
//                            c++;
//                            d++;
//                        }

//                        getDownload(2, 0);
//                        allImageDownload(0, 0);
//                        for (int i = 1; i < mDownloads.size(); i++) {
//                            allImageDownload(i, 1);
//                        }
//                        getDownload(3, 0);
//                        allImageDownload(0, 0);
//                        for (int i = 1; i < mDownloads.size(); i++) {
//                            allImageDownload(i, 1);
//                        }
//                        getDownload(4, 0);
//                        allImageDownload(0, 0);
//                        for (int i = 1; i < mDownloads.size(); i++) {
//                            allImageDownload(i, 1);
//                            FilesDownload.getInstance(MainActivity.this, "", 1).start();
//                        }
//                        allSoundDownload();
//                        FilesDownload.getInstance(MainActivity.this, "", 0).start();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//
//
//                            }
//                        }, 480000);


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                        B_URL = Global.ALTER_URL;
                        FilesDownload.getInstance(MainActivity.this, "").start();
                        Utils.log("s", "s");
                    }
                }
        );
    }

    // it will help to know phone is connected to internet or not
    // in this case ACCESS_NETWORK_STATE is needed as permission


    private void saveLevelToDb() {
        for (MLevel data : Global.levels) {
            database.addLevelFromJson(data);
        }
    }

    private void saveSubLevelToDb() {
        for (MSubLevel data2 : Global.mSubLevelArrayList) {
            database.addSubFromJsom(data2);
        }
    }

    private void saveEnglishContentsOfAllLevelToDb() {
        for (MAllContent mAllContent : Global.English) {
            database.addAllContentsData(mAllContent);
        }
    }


    private void saveBanglaContentsOfAllLevelToDb() {
        for (MAllContent mAllContent : Global.BANGLA) {
            database.addAllContentsData(mAllContent);
        }
    }

    private void saveMathContentsOfAllLevelToDb() {
        for (MAllContent mAllContent : Global.Maths) {
            database.addAllContentsData(mAllContent);
        }
    }

    private void saveBanglaMathContentsOfAllLevelToDb() {
        for (MAllContent mAllContent : Global.BANGLA_Maths) {
            database.addAllContentsData(mAllContent);
        }
    }

    private void saveEnglishWordsToDb() {
        for (MWords mWords : Global.English_words) {
            database.addAllWordsData(mWords);
        }
    }

    private void saveBanglaWordsToDb() {
        for (MWords mWords : Global.BANGLA_words) {
            database.addAllWordsData(mWords);
        }
    }


    private void saveMathWordsToDb() {
        for (MWords mWords : Global.MATH_words) {
            database.addAllWordsData(mWords);
        }
    }

    private void saveBanglaMathWordsToDb() {
        for (MWords mWords : Global.BANGLA_MATH_words) {
            database.addAllWordsData(mWords);
        }
    }


    public void allImageDownload() {
        FilesDownload filesDownload = FilesDownload.getInstance(this, bothImg);
        for (int i = 0; i < mDownloads.size(); i++) {
            mDownload = mDownloads.get(i);
            int init = mDownloads.get(0).getSubLevelId();
            int max = init + Global.LEVEL_DOWNLOAD;
            if (mDownload.getSubLevelId() < max) {
                filesDownload.addUrl(Global.IMAGE_URL + mDownloads.get(i).getUrl());
                mDownload.setIsDownload(1);

            }
            database.addDownloadData(mDownload);

        }
    }

    public void allImageDownload2() {
        FilesDownload filesDownload = FilesDownload.getInstance(this, bothImg);
        for (int i = 1; i < mDownloads.size(); i++) {
            mDownload = mDownloads.get(i);
            int init = mDownloads.get(i).getSubLevelId();
            int max = init + Global.LEVEL_DOWNLOAD;
            if (mDownload.getSubLevelId() < max) {
                filesDownload.addUrl(Global.IMAGE_URL + mDownloads.get(i).getUrl());
                mDownload.setIsDownload(1);
                database.addDownloadData(mDownload);

            }
        }
    }

    public void allSoundDownload() {
        FilesDownload filesDownload = FilesDownload.getInstance(this, bothSnd);
        for (int i = 0; i < mDownloads.size(); i++) {
            int init = mDownloads.get(0).getSubLevelId();
            int max = init + Global.LEVEL_DOWNLOAD;
            mDownload = mDownloads.get(i);
            if (mDownload.getSubLevelId() < max) {
                filesDownload.addUrl(Global.BASE_SOUND_URL + mDownloads.get(i).getUrl());
                mDownload.setIsDownload(1);
                database.addDownloadData(mDownload);
            }
        }
//        filesDownload.start();
    }


    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game_exit);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final Button btnYes = (Button) dialog.findViewById(R.id.btnYes);
        final Button btnNO = (Button) dialog.findViewById(R.id.btnNo);
        TextView diaTxt = (TextView) dialog.findViewById(R.id.diaTxt);
        TextView diaTxt2 = (TextView) dialog.findViewById(R.id.diaTxt2);
        Utils.setFont(this, "carterone", btnNO, btnYes, diaTxt, diaTxt2);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void exitYes() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        levelsDatas = database.getLevelAllData();
        if (levelsDatas.size() == 0) {
            Utils.toastMassage(this, "No Data");
            return;

        }
        if (v.getId() == R.id.btnBangla) {
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", levelsDatas.get(0).getLid());
            intent.putExtra("name", levelsDatas.get(0).getName());

            startActivity(intent);
        } else if (v.getId() == R.id.btnBanglaMath) {
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", levelsDatas.get(1).getLid());
            intent.putExtra("name", levelsDatas.get(1).getName());

            startActivity(intent);
        } else if (v.getId() == R.id.btnEnglish) {
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", levelsDatas.get(2).getLid());
            intent.putExtra("name", levelsDatas.get(2).getName());
            startActivity(intent);
        } else if (v.getId() == R.id.btnMath) {
            Intent intent = new Intent(MainActivity.this, SubLevelActivity.class);
            intent.putExtra("id", levelsDatas.get(3).getLid());
            intent.putExtra("name", levelsDatas.get(3).getName());
            startActivity(intent);
        } else if (v.getId() == R.id.btnSetting) {
            DialogSoundOnOff.dialogShow(this);

        } else if (v.getId() == R.id.btnResult) {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dia_result);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView txtBanglaPoint = (TextView) dialog.findViewById(R.id.txtBanglaPoint);
            TextView txtEnglishPoint = (TextView) dialog.findViewById(R.id.txtEnglishPoint);
            TextView txtBMPoint = (TextView) dialog.findViewById(R.id.txtBanglaMathPoint);
            TextView txtMathPoint = (TextView) dialog.findViewById(R.id.txtMathPoint);
            TextView txtBangla = (TextView) dialog.findViewById(R.id.txtBangla);
            TextView txtEng = (TextView) dialog.findViewById(R.id.txtEnglish1);
            TextView txtBMath = (TextView) dialog.findViewById(R.id.txtBanglaMa);
            TextView txtMath = (TextView) dialog.findViewById(R.id.txtMath1);
            txtBangla.setTextColor(0xff00ff00);
            txtBanglaPoint.setTextColor(0xff00ff00);
            txtEng.setTextColor(0xffff0000);
            txtEnglishPoint.setTextColor(0xffff0000);
            txtBMath.setTextColor(0xffffff00);
            txtBMPoint.setTextColor(0xffffff00);
            txtMath.setTextColor(0xffff00ff);
            txtMathPoint.setTextColor(0xffff00ff);
            int totalBPoint = database.getLockTotalPointData(1);
            txtBanglaPoint.setText(Utils.convertNum(totalBPoint + ""));
            int totalEPoint = database.getLockTotalPointData(3);
            txtEnglishPoint.setText(totalEPoint + "");
            int totalMPoint = database.getLockTotalPointData(4);
            txtMathPoint.setText(totalMPoint + "");
            int totalBMPoint = database.getLockTotalPointData(2);
            txtBMPoint.setText(Utils.convertNum(totalBMPoint + ""));
            dialog.show();

        }

    }
}
