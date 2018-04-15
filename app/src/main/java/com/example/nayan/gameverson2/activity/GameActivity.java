package com.example.nayan.gameverson2.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.adapter.GameAdapter;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MData;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.model.MWords;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
import com.example.nayan.gameverson2.tools.FilesDownload;
import com.example.nayan.gameverson2.tools.Global;
import com.example.nayan.gameverson2.tools.MyGoogleAnalytics;
import com.example.nayan.gameverson2.tools.SpacesItemDecoration;
import com.example.nayan.gameverson2.tools.Utils;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.nayan.gameverson2.activity.MainActivity.bothImg;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private static GameActivity gameActivity;
    private static MLevel mLevel;
    private static MAllContent mContents;
    public String subLevelName, how;
    public String parentName;
    public TextView txtName, txtTotalPoint, txtSubName;
    LinearLayout popUI;
    private MData mData;
    private MSubLevel mSubLevel = new MSubLevel();
    private ArrayList<MAllContent> mAllContentArrayList;
    private ArrayList<MWords> wordsList;
    private ImageView imgSetting, imageView, imgHelp;
    private RecyclerView recyclerView;
    //    private Context context;
    private GameAdapter gameAdapter;
    private DatabaseHelper database;
    private MLock mLock;
    int content;

    //    private GameActivity(){
//
//    }
    public static GameActivity getInstance() {
        return gameActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_activity);
//        VungleAdManager.getInstance(this).play();


        init();
        MyGoogleAnalytics.getInstance().setupAnalytics("Game Activity");
        getLocalData(content);
        getIsSaveDataFromDb(Global.levelId, Global.subLevelId);
        prepareDisplay();

//        getPopUp();
//        if (Global.levelId == 1) {
//            if (Global.subLevelId == 2) {
//                MainActivity.getInstance().banglaImage(6);
//                MainActivity.getInstance().imageDownload();
//            }
//        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    public void init() {
        mData = new MData();
        imgHelp = (ImageView) findViewById(R.id.imgHelp);
        imgHelp.setOnClickListener(this);
        gameActivity = this;
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        txtName = (TextView) findViewById(R.id.txtNameGame);
        txtSubName = (TextView) findViewById(R.id.txtNameGameSub);
        txtTotalPoint = (TextView) findViewById(R.id.txtTotalPoint);
        database = new DatabaseHelper(this);
        imgSetting = (ImageView) findViewById(R.id.imgseting);
        imgSetting.setOnClickListener(this);
        mAllContentArrayList = new ArrayList<>();
        wordsList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new SpacesItemDecoration(7));
        Global.subLevelName = getIntent().getStringExtra("subLevelName");
        Global.logic = getIntent().getIntExtra("SLogic", 0);
        subLevelName = Global.subLevelName;
        Global.parentLevelName = getIntent().getStringExtra("parentLevelName");
        parentName = Global.parentLevelName;
        Global.SUB_INDEX_POSITION = getIntent().getIntExtra("index", 0);
        Global.pos = getIntent().getIntExtra("index", 0);
        Global.subLevelId = getIntent().getIntExtra("Sid", 0);
        Global.present_content = getIntent().getIntExtra("content", 0);
        content = getIntent().getIntExtra("content", 0);
        gameAdapter = new GameAdapter(this);
        Log.e("content", " size " + content);

    }

    public void getPopUp() {

        if (mLock.getPopup() == 0)
            diaRulesOfPlay(database.getPopUp(Global.levelId, Global.subLevelId));
        Log.e("pop", " is  " + mLock.getPopup());
        mLock.setLevel_id(Global.levelId);
        mLock.setSub_level_id(Global.subLevelId);
        mLock.setPopup(1);
        database.addLockData(mLock);


    }

    public void download() {
        if (!Utils.isInternetOn(this)) {
            Utils.log("Internet", "No Internet");
            return;
        }
        FilesDownload filesDownload = FilesDownload.getInstance(GameActivity.this, bothImg);
        for (int i = 0; i < MainActivity.getInstance().uniquesUrls.size(); i++) {
            filesDownload.addUrl(Global.IMAGE_URL + MainActivity.getInstance().uniquesUrls.get(i));

        }
        FilesDownload.getInstance(GameActivity.this, "").start();
    }

    public void getIsSaveDataFromDb(int level, int subLevel) {
        mData = database.getIsSavePoint(level, subLevel);
        Global.isSavePoint = mData.getIsSavePoint();
        Log.e("isSavePoint", " is " + Global.isSavePoint);
        Log.e("isSavePoint", " level " + level);
        Log.e("isSavePoint", " is " + subLevel);
    }

    public void getLocalData(int contents) {
//        ArrayList<MAllContent> contentArrayList1 = database.getAllContentsData(Global.levelId, content, 0, 0);
//        Log.e("contentList", " size " + contentArrayList1.size());


        mSubLevel = database.getSubLevelData(Global.levelId).get(Global.SUB_INDEX_POSITION);
        mLock = database.getLocalData(Global.levelId, Global.subLevelId);

        Global.popUp = mLock.getPopup();

        Log.e("TEST", Global.levelId + ":" + Global.subLevelId + ":" + content);

        if (Global.logic == 1) {


            mAllContentArrayList = database.getAllContentsData(Global.levelId, contents);
//            Collections.shuffle(mAllContentArrayList);
        }

//        if (Global.logic == 2) {
//
//            if (Global.levelId == 1) {
//                mAllContentArrayList = database.getBanglaContentsContentsData(2);
//            } else if (Global.levelId == 2) {
//                mAllContentArrayList = database.getBanglaMathContentsContentsData(2);
//            } else if (Global.levelId == 3) {
//                mAllContentArrayList = database.getEnglishContentsContentsData(2);
//            } else if (Global.levelId == 4) {
//                mAllContentArrayList = database.getMathContentsContentsData();
//            }
//
//            Collections.shuffle(mAllContentArrayList);
//        }
        else if (Global.logic == 2) {

            ArrayList<MAllContent> realAssets = new ArrayList<>();
            realAssets = database.getAllContentsData(Global.levelId, contents);
            mAllContentArrayList = generatesTxtSen(realAssets);
            Collections.shuffle(mAllContentArrayList);
        } else if (Global.logic == 3) {

            mAllContentArrayList = database.getAllContentsData(Global.levelId, contents);
            Collections.shuffle(mAllContentArrayList);
        } else if (Global.logic == 4) {

            ArrayList<MAllContent> realAssets = new ArrayList<>();
            realAssets = database.getAllContentsData(Global.levelId, contents);

            mAllContentArrayList = generatesTxtImg(realAssets);
            Collections.shuffle(mAllContentArrayList);
        }

        Log.e("ERR", "data size : " + mAllContentArrayList.size());
    }

    public void diaRulesOfPlay(String s) {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_instruction);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        popUI = (LinearLayout) dialog.findViewById(R.id.popUpUI);
        if (Global.levelId == 1) {
            Utils.changeUIcolor(this, Global.uriBangla, popUI);
//            txtLevelName.setTextColor(0xff00ff00);
        } else if (Global.levelId == 2) {
            Utils.changeUIcolor(this, Global.uriOngko, popUI);
//            txtLevelName.setTextColor(0xffffff00);
        } else if (Global.levelId == 3) {
            Utils.changeUIcolor(this, Global.uriEnglish, popUI);
        } else if (Global.levelId == 4) {
//            imageView.setImageResource(R.drawable.red_coins);
            Utils.changeUIcolor(this, Global.uriMath, popUI);
        }
        TextView txtRule = (TextView) dialog.findViewById(R.id.txtRules);
        txtRule.setText(s);
        Button close = (Button) dialog.findViewById(R.id.btnDismiss);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private ArrayList<MAllContent> generateAssets(ArrayList<MAllContent> realAssets) {
        int count = realAssets.size();
        ArrayList<MAllContent> tempAsset = new ArrayList<>();
        for (MAllContent mContents : realAssets) {
            tempAsset.add(mContents);
            count++;
            MAllContent asset1 = new MAllContent();
            asset1.setPresentType(mContents.getPresentType());
            asset1.setTxt(mContents.getTxt());
            asset1.setMid(count);
            tempAsset.add(asset1);
        }
        return tempAsset;
    }

    private ArrayList<MAllContent> generatesTxtSen(ArrayList<MAllContent> realTxtSen) {
//        int count = realTxtSen.size();
        int count = 550;
        ArrayList<MAllContent> tempTxtSen = new ArrayList<>();
        for (MAllContent mContents : realTxtSen) {
            tempTxtSen.add(mContents);
            count++;
            MAllContent contents = new MAllContent();
            contents.setSen(mContents.getSen());
            contents.setMid(count);
//            mContents.setCp();
//            contents.setPresentId(mContents.getPresentId());
            contents.setPresentType(mContents.getPresentType());
            tempTxtSen.add(contents);
        }
        return tempTxtSen;
    }

    private ArrayList<MAllContent> generatesTxtImg(ArrayList<MAllContent> realTxtSen) {
        int count = realTxtSen.size();
        ArrayList<MAllContent> tempTxtSen = new ArrayList<>();
        for (MAllContent mContents : realTxtSen) {
            tempTxtSen.add(mContents);
            count++;
            MAllContent contents = new MAllContent();
            contents.setImg(mContents.getImg());
            contents.setMid(count);
//            contents.setPresentId(mContents.getPresentId());
            contents.setAud(mContents.getAud());
            contents.setPresentType(mContents.getPresentType());
            tempTxtSen.add(contents);
        }
        return tempTxtSen;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    public void refresh(int index, int contents) {
        subLevelName = Global.parentName.get(index).getName();
        Global.present_content = Global.parentName.get(index).getContent();
        how = Global.parentName.get(index).getHowto();
        Log.e("sublevel name", "s  n :" + subLevelName);
        parentName = Global.parentName.get(index).getParentName();
        Log.e("index ", "posi =" + Global.SUB_INDEX_POSITION);
        getLocalData(contents);
        prepareDisplay();
    }

    public void prepareDisplay() {

        Utils.setFont(this, "carterone", txtName, txtTotalPoint);
        Global.totalPoint = mLock.getTotal_pont();
        if (Global.levelId == 1) {
            txtTotalPoint.setText(Utils.convertNum(Global.totalPoint + ""));
        }
        if (Global.levelId == 2) {
            txtTotalPoint.setText(Utils.convertNum(Global.totalPoint + ""));
        }
        if (Global.levelId == 3) {
            txtTotalPoint.setText(Global.totalPoint + "");
        }
        if (Global.levelId == 4) {
            txtTotalPoint.setText(Global.totalPoint + "");
        }
//        txtTotalPoint.setText(Global.totalPoint + "");
//        txtName.setText(parentName + "(" + subLevelName + ")");
        txtSubName.setText(" - " + subLevelName + "");


        int item = Utils.getScreenSize(this, 90);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(gameAdapter);
        gameAdapter.setData(mAllContentArrayList);

        if (Global.levelId == 1) {
            imageView.setImageResource(R.drawable.grren_coins);
            txtName.setBackgroundResource(R.drawable.bangla);
            Utils.setFont(this, "BenSenHandwriting", txtSubName);


        } else if (Global.levelId == 2) {
            imageView.setImageResource(R.drawable.yellow_coins);
            txtName.setBackgroundResource(R.drawable.ongko);
            Utils.setFont(this, "BenSenHandwriting", txtSubName);

        } else if (Global.levelId == 3) {
            imageView.setImageResource(R.drawable.red_coins);
            txtName.setBackgroundResource(R.drawable.english);
            Utils.setFont(this, "carterone", txtSubName);
//            txtSubName.setTextSize(24);

        } else if (Global.levelId == 4) {
            imageView.setImageResource(R.drawable.violet_coins);
            txtName.setBackgroundResource(R.drawable.math);
            Utils.setFont(this, "carterone", txtSubName);
//            txtSubName.setTextSize(24);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgHelp || v.getId() == R.id.imageView) {

            diaRulesOfPlay(database.getPopUp(Global.levelId, Global.subLevelId));
        }
    }
}
