package com.example.nayan.gameverson2.tools;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.model.MPost;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;

import cz.msebera.android.httpclient.Header;

/**
 * Created by NAYAN on 8/25/2016.
 */
public class Utils {

    public static boolean isSoundPlay = true;
    public static int bestPoint, presentPoint;
    public static int widthSize, heightSize;
    static MediaPlayer mediaPlayer;
    public static final String MYPREF = "mPre";



    public static String convertToBangla(String number) {
        number = number.
                replace("0", "০").
                replace("1", "১").
                replace("2", "২").
                replace("3", "৩").
                replace("4", "৪").
                replace("5", "৫").
                replace("6", "৬").
                replace("7", "৭").
                replace("8", "৮").
                replace("9", "৯");

        return number;
    }

    public static void getSound(Context context, int path) {
        if (isSoundPlay) {

            if (mediaPlayer != null) {
                mediaPlayer.stop();
                //mediaPlayer.reset();
                mediaPlayer.release();
            }
            Log.e("CONTEXT", "value :" + context + ":" + path);
            mediaPlayer = MediaPlayer.create(context, path);
            mediaPlayer.start();
            Log.e("log", "playing");
        }
    }

    public static void PlaySound(String path) {
        if (isSoundPlay) {

            if (mediaPlayer != null) {
                mediaPlayer.stop();
                //mediaPlayer.reset();
                mediaPlayer.release();
            }
//            mediaPlayer = MediaPlayer.create(context, path);
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.start();

            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.e("log", "playing");
        }
    }

    // get android device id
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getPhoneGmailAcc(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account[] accounts = accountManager.getAccountsByType("com.google");
        return accounts.length > 0 ? accounts[0].name.trim().toLowerCase() : "null";

    }


    public static void postDataFromDatabase(final MPost mPost) {
        //need params variable name of data url
        final Gson gson = new Gson();
        final RequestParams params = new RequestParams();
        AsyncHttpClient client = new AsyncHttpClient();
        params.put("post_games", gson.toJson(mPost));
        Log.e("post", "is" + gson.toJson(mPost));
        client.post("http://www.radhooni.com/content/match_game/v1/games_data_save.php", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("post", "success" + response);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("post", "fail");
            }
        });
    }

    public static void logIn(String email, String pass, String deviceId) {
        //need url
        final Gson gson = new Gson();
        final RequestParams params = new RequestParams();
        AsyncHttpClient client = new AsyncHttpClient();
        params.put("userEmail", email);
        params.put("password", pass);
        params.put("deviceId", deviceId);
        Log.e("isParams", params.toString());
        client.get("http://www.radhooni.com/content/match_game/v1/users.php", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e("gameStatus", " is response :" + response.toString());
//                mPost[0] = gson.fromJson(response.toString(), MPost.class);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    public static String convertNum(String num) {
        if (num.length() > 0) {
            num = num.replace("0", "০").replace("1", "১").replace("2", "২")
                    .replace("3", "৩").replace("4", "৪").replace("5", "৫")
                    .replace("6", "৬").replace("7", "৭").replace("8", "৮")
                    .replace("9", "৯");
        }


        return num;
    }

    public static void changeUIcolor(Context context, String color, View view) {
        int imageResourceGreen = context.getResources().getIdentifier(color, null, context.getPackageName());
        Drawable resGreen = context.getResources().getDrawable(imageResourceGreen);
        view.setBackground(resGreen);
    }

    public static void diaRulesOfPlay(Context context, String s) {
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.game_instruction);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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

    public static String databasePassKey(String emailName, String deviceId) {
        String firstChOfEmail = emailName.substring(0, 1);
        String lastChOfEmail = emailName.substring(emailName.indexOf("@") - 1, emailName.indexOf("@"));
        String firstNumOfDeviceId = deviceId.substring(0, 1);
        String secondNumOfDeviceId = deviceId.substring(deviceId.length() - 1);
        return firstChOfEmail + lastChOfEmail + firstNumOfDeviceId + secondNumOfDeviceId;
    }

    public static void toastMassage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void zoom(View view, boolean isLeft) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.2f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.2f);
        animator2.setDuration(2000);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        animator2.setRepeatMode(ValueAnimator.REVERSE);
        animator2.start();
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(2000);
        animator.start();
    }

    public static void rotation(View view, boolean isLeft) {
        view.setPivotX(view.getX() + view.getWidth() / 2);
        view.setPivotY(view.getY() + view.getHeight() / 2);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", 0, 180);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(1);
        animator.setDuration(2000);
        animator.start();
    }

    public static int getScreenSize(Activity activity, int itemSize) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        itemSize = getPixel(activity, itemSize);
        int width = size.x;
        int height = size.y;
        int item = (width - 30) / (itemSize);
        return item;

    }

    public static int getPixel(Context context, int pixel) {
        float density = context.getResources().getDisplayMetrics().density;
        int paddingDp = (int) (pixel * density);
        return paddingDp;
    }

    public static void moveAnimation(Object target, Object target2) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "translationX", -widthSize, widthSize);
        animator.setDuration(7500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(target2, "translationX", widthSize, -widthSize);
        animator1.setDuration(7500);
        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animator1.start();

    }

    public static int randInt(int min, int max) {


        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static void move(final View view, final View view2) {

//        count=count+10;
//        Log.e("count", "is" + count);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
//                getScreenSize(MainActivity.this);

                view2.setY(randInt(5, heightSize - (heightSize / 4)));
//                view2.setX(280);
                view.setY(randInt(5, heightSize - (heightSize / 4)));
//                view.setX(-200);
                move(view, view2);
            }
        }, 7500);

    }

    public static void log(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void getScreenSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        widthSize = (width - 5);
        heightSize = (height - 50);
        Log.e("width", "size" + width);
        Log.e("height", "size" + height);

    }

    public static boolean isInternetOn(Context context) {

        try {
            ConnectivityManager con = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifi, mobile;
            wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            mobile = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifi.isConnectedOrConnecting() || mobile.isConnectedOrConnecting()) {
                return true;
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

//    public static void moveAnimation(Object target, Object target2) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "translationX", -280, 280);
//        animator.setDuration(9000);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.start();
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(target2, "translationX", 280, -280);
//        animator1.setDuration(9000);
//        animator1.setRepeatCount(ValueAnimator.INFINITE);
//        animator1.start();
//
//    }

    public static String getIntToStar(int starCount) {
        String fillStar = "\u2605";
        String blankStar = "\u2606";
        String star = "";

        for (int i = 0; i < starCount; i++) {
            star = star.concat(" " + fillStar);
        }
        for (int j = (3 - starCount); j > 0; j--) {
            star = star.concat(" " + blankStar);
        }

        return star;
    }

    public static void changeFont(TextView tx, Context context) {
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/carterone.ttf");

        tx.setTypeface(custom_font);
    }

    public static void changeFontAnotherWay(TextView tx, Context context) {

        AssetManager am = context.getApplicationContext().getAssets();

        Typeface typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "carterone.ttf"));

        tx.setTypeface(typeface);
    }

    public static void setFont(Context context, String font, TextView... textViews) {

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + font + ".ttf");
        for (TextView textView : textViews) {
            textView.setTypeface(typeface);
        }
    }

    public static String getPREF( String key) {
        SharedPreferences preferences = MyApplication.getMyInstance().getAppContext().getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        return preferences.getString(key, "null");
    }
    public static int getIntPREF(String key) {
        SharedPreferences preferences =  MyApplication.getMyInstance().getAppContext().getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    public static void savePref(String key, String value) {
        SharedPreferences preferences =  MyApplication.getMyInstance().getAppContext().getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }


}
