package com.example.nayan.gameverson2.tools;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;

/**
 * Created by NAYAN on 9/8/2016.
 */
public class DialogSoundOnOff {
    public static final String MYPREF = "mpref";
    public static final String KEY_IMAGE = "image";


    public static String getPREF(String key) {
        SharedPreferences preferences = MyApplication.getMyInstance().getAppContext().getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        return preferences.getString(key, "null");
    }

    public static int getIntPREF(String key) {
        SharedPreferences preferences = MyApplication.getMyInstance().getAppContext().getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    public static void savePref(String key, String value) {
        SharedPreferences preferences = MyApplication.getMyInstance().getAppContext().getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void dialogShow(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_setting);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final ImageView imgSound = (ImageView) dialog.findViewById(R.id.imgSoundOnOf);
        ImageView btnOK = (ImageView) dialog.findViewById(R.id.btnOk);
        ImageView btnCancel = (ImageView) dialog.findViewById(R.id.imgCancel);
        Button btnContuct = (Button) dialog.findViewById(R.id.btnContuctUs);
        TextView txtSound = (TextView) dialog.findViewById(R.id.txtSound);
        TextView txtContact = (TextView) dialog.findViewById(R.id.txtContuctUs);
        Utils.setFont(context, "carterone", txtSound, txtContact);
        btnContuct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + Global.ADMIN));
                    intent.putExtra(Intent.EXTRA_SUBJECT, Global.SUBJECT);
                    intent.putExtra(Intent.EXTRA_TEXT, Global.TEXT);
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    //TODO smth
                }
            }
        });
        String image;
        image = getPREF(KEY_IMAGE);
        if (image.equals(1 + "")) {
            Utils.isSoundPlay = true;
            imgSound.setImageResource(R.drawable.on);
        } else if (image.equals(0 + "")) {
            Utils.isSoundPlay = false;
            imgSound.setImageResource(R.drawable.off);
        }
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isSoundPlay == false) {
                    Utils.isSoundPlay = true;
                    imgSound.setImageResource(R.drawable.on);
                    savePref(KEY_IMAGE, 1 + "");
                } else {
                    Utils.isSoundPlay = false;
                    imgSound.setImageResource(R.drawable.off);
                    savePref(KEY_IMAGE, 0 + "");
                }
            }
        });
        dialog.show();
    }

}
