package com.example.nayan.gameverson2.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.adapter.SubLevelAdapter;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MColor;
import com.example.nayan.gameverson2.model.MLevel;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
import com.example.nayan.gameverson2.tools.DialogSoundOnOff;
import com.example.nayan.gameverson2.tools.FilesDownload;
import com.example.nayan.gameverson2.tools.Global;
import com.example.nayan.gameverson2.tools.MyGoogleAnalytics;
import com.example.nayan.gameverson2.tools.Utils;

import java.util.ArrayList;

import static com.example.nayan.gameverson2.activity.MainActivity.bothImg;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class SubLevelActivity extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<MSubLevel> mSubLevels;
    private static int value;
    private SubLevelAdapter subLevelAdapter;

    private static ArrayList<MLevel> mLevels;
    private static ArrayList<MAllContent> mAllContents;
    private static MSubLevel mSubLevel = new MSubLevel();
    private static MLevel mLevel = new MLevel();
    int totalPoint;
    private DatabaseHelper database;
    private RecyclerView recyclerView;
    private TextView txtLevelName, txtAllTotal_ponts, txtLevelSelect;
    private MAllContent mAllContent;
    private String lName;
    private MLock mLock;
    private Button back, btnSubSetting;
    private LinearLayout changeColor;
    private ImageView imageView, imgLevelName, imageHelp;
    private MColor mColor;
    private ArrayList<MColor> mColors;
    private static SubLevelActivity subLevelActivity;

    public static SubLevelActivity getInstane() {
        return subLevelActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_level_activity);

        Global.levelName = getIntent().getStringExtra("name");
        Global.levelId = getIntent().getIntExtra("id", 0);
        Log.e("level id", " is " + Global.levelId);
        value = Global.levelId;
        lName = Global.levelName;
        Log.e("log", "is" + value);

        init();
        MyGoogleAnalytics.getInstance().setupAnalytics("SubLevel Activity");
        getLocalData();
        prepareDisplay();
//        color();
//        imageDownload();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getLocalData();
        prepareDisplay();
    }

    private void downloadAssets() {
//        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//            ArrayList<String> uniqueImage = new ArrayList<>();
//            for (int i = 0; i < Utils.BANGLA.size(); i++) {
//                ArrayList<MWords> assetArrayList = database.getBanglaWordsData(Utils.BANGLA.get(i).getMid());
//                for (int j = 0; j < assetArrayList.size(); j++) {
//                    if (!uniqueImage.contains(assetArrayList.get(j).getWimg())) {
//                        uniqueImage.add(assetArrayList.get(j).getWimg());
//                    }
//                }
//            }
//            for (int i = 0; i < uniqueImage.size(); i++) {
//                Log.e("DOWNLOAD_PATH", "Path:" + MainActivity.getPath(uniqueImage.get(i)));
//                File file = new File(MainActivity.getPath(uniqueImage.get(i)));
//                if (!file.exists()) {
//                    new DownLoadAsyncTask(this, MainActivity.getPath(uniqueImage.get(i))).execute(IMAGE_URL + uniqueImage.get(i));
//                }
//
//            }
//        } else requestStoragePermission();


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

            finish();

        } else if (id == R.id.action_settings) {
            DialogSoundOnOff.dialogShow(this);
            return true;
        }

        //id unused
        return super.onOptionsItemSelected(item);
    }


    private void init() {
        subLevelActivity = this;
        mColors = new ArrayList<>();
        mLevels = new ArrayList<>();
        mAllContent = new MAllContent();
        mAllContents = new ArrayList<>();
        imageHelp = (ImageView) findViewById(R.id.imgHelpSub);
        imageHelp.setOnClickListener(this);
        txtLevelSelect = (TextView) findViewById(R.id.levelSelect);
        mLock = new MLock();
        imageView = (ImageView) findViewById(R.id.imageViewSub);
        imageView.setOnClickListener(this);
        changeColor = (LinearLayout) findViewById(R.id.changeColor);
        back = (Button) findViewById(R.id.back);
        btnSubSetting = (Button) findViewById(R.id.btnSubSetting);
        back.setOnClickListener(this);
        btnSubSetting.setOnClickListener(this);
        Global.levels = new ArrayList<>();
        mLevels = new ArrayList<>();
        mSubLevels = new ArrayList<>();
        database = new DatabaseHelper(this);
        mLevels = database.getLevelData(value);

        txtLevelName = (TextView) findViewById(R.id.imgLevelName);
        txtAllTotal_ponts = (TextView) findViewById(R.id.txtAllTotalPoints);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        subLevelAdapter = new SubLevelAdapter(this);


    }

    public void color() {
//        String start = DialogSoundOnOff.getPREF(SubLevelActivity.this, Global.levelId + "");
//        int s = Integer.valueOf(start);
//        mColor = database.getColor(Global.levelId, Global.subLevelId);
//        for (int i = 0; i < s; i++) {
//            mColor = new MColor();
//            mColor.setLevelId(Global.levelId);
//            mColor.setSubLevelId(Global.subLevelId);
//            mColor.setColor(1);
//            mColors.add(mColor);
//            database.addcolor(mColor);
//        }
//        DatabaseHelper db = new DatabaseHelper(SubLevelActivity.this);
//        MLock lock;
//        for (int i = 0; i < s; i++) {
//            lock = new MLock();
//            lock = db.getLocalData(Global.levelId, mSubLevels.get(i).getLid());
//            lock.setLevel_id(1);
//            lock.setSub_level_id(mSubLevels.get(i).getLid());
//            Log.e("LOGIC", "sid:" + lock.getSub_level_id());
//            lock.setColor(1);
//            db.addLockData(lock);
//            mSubLevels.get(i).setColor(1);
//        }

        String maxContent = Utils.getPREF( Global.levelId + "");
        int m = Integer.valueOf(maxContent);
        for (int i = 0; i < mSubLevels.size(); i++) {
            if (mSubLevels.get(i).getContent() <= m) {
                mSubLevels.get(i).setIsDownload(1);
            }
        }
        subLevelAdapter.notifyDataSetChanged();
    }

    public void download() {
        if (!Utils.isInternetOn(this)) {
            Utils.log("Internet", "No Internet");
            return;
        }
        FilesDownload filesDownload = FilesDownload.getInstance(SubLevelActivity.this, bothImg);
        for (int i = 0; i < MainActivity.getInstance().uniquesUrls.size(); i++) {
            filesDownload.addUrl(Global.IMAGE_URL + MainActivity.getInstance().uniquesUrls.get(i));

        }
        FilesDownload.getInstance(SubLevelActivity.this, "").start();
    }

    private void getLocalData() {
        mAllContents = database.getAllContentsData(Global.levelId, Global.subLevelId);
        Log.e("allData", "is" + mAllContents.size());

        mLock = database.getLocalData(Global.levelId, Global.subLevelId);
        mSubLevels = database.getSubLevelData(value);
        Log.e("level ", " level id " + value);

        Log.e("level", " is level number " + mSubLevels.size());

        if (mSubLevels.size() < 1) {
            Utils.toastMassage(this, "Empty Data");
            return;
        }
        Global.parentName = mSubLevels;
//        mLevels = database.getLevelData(mLevel.getContent());
        Log.e("getDb", "sublevel : " + mSubLevels.size());
//all sub level unlock for sub_level_activity
        mSubLevels.get(0).setUnlockNextLevel(1);
        color();
        subLevelAdapter.setData(mSubLevels);
        totalPoint = database.getLockTotalPointData(Global.levelId);

    }


    private void prepareDisplay() {
        Utils.setFont(this, "carterone", txtAllTotal_ponts, txtLevelName, txtLevelSelect);
        if (value == 1) {
            imageView.setImageResource(R.drawable.grren_coins);
            Utils.changeUIcolor(this, Global.uriBangla, changeColor);
            txtLevelName.setBackgroundResource(R.drawable.bangla);
//            txtLevelName.setTextColor(0xff00ff00);
        } else if (value == 2) {
            imageView.setImageResource(R.drawable.yellow_coins);
            Utils.changeUIcolor(this, Global.uriOngko, changeColor);
            txtLevelName.setBackgroundResource(R.drawable.ongko);
//            txtLevelName.setTextColor(0xffffff00);
        } else if (value == 3) {
            imageView.setImageResource(R.drawable.red_coins);
            Utils.changeUIcolor(this, Global.uriEnglish, changeColor);
            txtLevelName.setBackgroundResource(R.drawable.english);
        } else if (value == 4) {
//            imageView.setImageResource(R.drawable.red_coins);
            Utils.changeUIcolor(this, Global.uriMath, changeColor);
            txtLevelName.setBackgroundResource(R.drawable.math);
        }
        int item = Utils.getScreenSize(this, 180);
//        DatabaseHelper helper=new DatabaseHelper(this);
//        MLock lock=new MLock();
//        lock=helper.getLocalData(Global.subLevelId);
//        Global.totalPoint=lock.getTotal_pont();

        Global.ALL_TOTAL_POINT = mLock.getAll_total_point();
        Log.e("all", "item: " + item);
        if (Global.levelId == 1) {
            txtAllTotal_ponts.setText(Utils.convertNum(totalPoint + ""));
        }
        if (Global.levelId == 2) {
            txtAllTotal_ponts.setText(Utils.convertNum(totalPoint + ""));
        }
        if (Global.levelId == 3) {
            txtAllTotal_ponts.setText(totalPoint + "");
        }
        if (Global.levelId == 4) {
            txtAllTotal_ponts.setText(totalPoint + "");
        }

//        recyclerView.setHasFixedSize(true);

//        txtLevelName.setText(lName);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(subLevelAdapter);
    }

    public void diaRulesOfPlay(String s) {
        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_instruction);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LinearLayout popUI = (LinearLayout) dialog.findViewById(R.id.popUpUI);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            finish();
        } else if (v.getId() == R.id.btnSubSetting) {
            DialogSoundOnOff.dialogShow(SubLevelActivity.this);
        } else if (v.getId() == R.id.imageViewSub || v.getId() == R.id.imgHelpSub) {

            diaRulesOfPlay(database.getPopUpForLevel(Global.levelId));

        }
    }
}
