package com.example.nayan.gameverson2.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.activity.GameActivity;
import com.example.nayan.gameverson2.activity.MainActivity;
import com.example.nayan.gameverson2.activity.SubLevelActivity;
import com.example.nayan.gameverson2.model.MAllContent;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
import com.example.nayan.gameverson2.tools.DialogSoundOnOff;
import com.example.nayan.gameverson2.tools.FilesDownload;
import com.example.nayan.gameverson2.tools.GameLogic;
import com.example.nayan.gameverson2.tools.Global;
import com.example.nayan.gameverson2.tools.Utils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import static com.example.nayan.gameverson2.activity.MainActivity.bothImg;
import static com.example.nayan.gameverson2.activity.SubLevelActivity.mSubLevels;

/**
 * Created by NAYAN on 11/24/2016.
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewholder> {

    String sounds = "sounds";
    String mainSounds = "m_sounds";
    String imageWords = "WImage";
    DatabaseHelper db;
    private ArrayList<MAllContent> textArrayList;
    private MAllContent mContent = new MAllContent();
    private Context context;
    private LayoutInflater inflater;
    private GameLogic gameLogic;
    private int countPoint, present;


    public GameAdapter(Context context) {
        this.context = context;

        textArrayList = new ArrayList<>();
        db = new DatabaseHelper(context);

        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<MAllContent> textArraylist) {
        this.textArrayList = textArraylist;

        Log.e("log", "setdata:" + textArraylist.size());
        gameLogic = GameLogic.getInstance(context);
        gameLogic.callData(textArraylist, this);

        notifyDataSetChanged();
    }


    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.image_row, parent, false);
        MyViewholder viewholder = new MyViewholder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        mContent = textArrayList.get(position);
        if (Global.logic == 3) {
            if (mContent.getMatch() == 1) {
                holder.imgAnim2.setImageResource(R.drawable.green_panel);

            } else {
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContent.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);

        }
//main logic 2 make comments for sub_level_activity
        if (Global.logic == 2) {
            holder.txtContents.setTextColor(0xffff00ff);
            if (mContent.getTxt() == null || mContent.getTxt().equals("")) {
                holder.txtContents.setText(mContent.getSen());


            } else {
                holder.txtContents.setText(mContent.getTxt());
            }
            if (mContent.getMatch() == 1) {
                holder.imgAnim2.setImageResource(R.drawable.green_panel);
            } else {
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(0);
            }

        } else if (Global.logic == 1) {

            if (mContent.getMatch() == 1) {

            } else {
                holder.txtContents.setBackgroundColor(0);
            }
            holder.txtContents.setText(mContent.getTxt());
            holder.txtContents.setTextColor(0xffff00ff);
        } else if (Global.logic == 4) {
            holder.txtContents.setTextColor(0xffff00ff);
            holder.txtContents.setTextSize(20);
            holder.imgAnim2.setImageResource(R.drawable.green_panel);
            if (mContent.getTxt() == null || mContent.getTxt().equals("")) {

                holder.imgAnim.setVisibility(View.VISIBLE);
                holder.txtContents.setText("");
                Log.e("image e", "img :" + Global.IMAGE_URL + mContent.getImg());
                String loc = "loc";
                if (Global.levelId == 1) {
                    loc = MainActivity.image;
                } else if (Global.levelId == 2) {
                    loc = MainActivity.image;
                } else if (Global.levelId == 3) {
                    loc = MainActivity.image;
                } else if (Global.levelId == 4) {
                    loc = MainActivity.image;
                }
                Bitmap bmp = BitmapFactory.decodeFile(loc + "/" + mContent.getImg());

                holder.imgAnim.setImageBitmap(bmp);

            } else {
                holder.txtContents.setText(mContent.getTxt());
                holder.imgAnim.setVisibility(View.GONE);
            }
            if (mContent.getMatch() == 1) {
                holder.imgAnim2.setImageResource(R.drawable.green_panel);
            } else {
                holder.imgAnim2.setImageResource(R.drawable.yellow_panel);
                holder.txtContents.setBackgroundColor(0);
            }
        }


    }

    @Override
    public int getItemCount() {
        return textArrayList.size();
    }
    class MyViewholder extends RecyclerView.ViewHolder {
        TextView txtContents;
        private ImageView imgAnim;
        private ImageView imgAnim2;

        public MyViewholder(final View itemView) {
            super(itemView);
            imgAnim = (ImageView) itemView.findViewById(R.id.imgImage);
            imgAnim2 = (ImageView) itemView.findViewById(R.id.imganim2);
            txtContents = (TextView) itemView.findViewById(R.id.textContents);
            if (Global.levelId == 1) {
                Utils.setFont(context, "BenSen", txtContents);
            }
            if (Global.levelId == 2) {
                Utils.setFont(context, "BenSen", txtContents);
            }
            if (Global.levelId == 3) {
                Utils.setFont(context, "carterone", txtContents);
            }
            if (Global.levelId == 4) {
                Utils.setFont(context, "carterone", txtContents);
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() < 0)
                        return;

                    mContent = textArrayList.get(getAdapterPosition());
                    Global.GAME_INDEX_POSITION = getAdapterPosition();


                    if (Global.logic == 3) {
//                        String mSound = "msound";
//                        if (Global.levelId == 1) {
//                            mSound = MainActivity.sounds;
//                        } else if (Global.levelId == 2) {
//                            mSound = MainActivity.sounds;
//                        } else if (Global.levelId == 3) {
//                            mSound = MainActivity.sounds;
//                        } else if (Global.levelId == 4) {
//                            mSound = MainActivity.sounds;
//                        }
//                        Utils.PlaySound(mSound + File.separator + mContent.getAud());
                        gameLogic.textClick(mContent, getAdapterPosition(), textArrayList.size(), itemView, txtContents, imgAnim2);
                    } else if (Global.logic == 2) {

                        gameLogic.imageClick(mContent, getAdapterPosition(), textArrayList.size(), itemView, imgAnim2);
                    } else if (Global.logic == 1) {

                        mContent.setWords(db.getAllWordsData(mContent.getMid()));
                        dialogShowWithWordArray(getAdapterPosition());
                    } else if (Global.logic == 4) {
                        gameLogic.forLevel2(itemView, mContent, textArrayList.size(), txtContents, getAdapterPosition(), imgAnim2);
                    }

                }
            });
        }
    }

    private void dialogShow(final int start, final int pos, final int level) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dia_download);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnOK = (Button) dialog.findViewById(R.id.btnYap);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnNop);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Global.levelId == 1) {
                    MainActivity.getInstance().banglaImage(start);
                    Log.e("content", " start bangla ");

                } else if (Global.levelId == 2) {
                    MainActivity.getInstance().ongkoImage(start);
                    Log.e("content", " start ongko ");
                } else if (Global.levelId == 3) {
                    MainActivity.getInstance().englishImage(start);
                    Log.e("content", " start english ");
                } else if (Global.levelId == 4) {
                    MainActivity.getInstance().mathImage(start);
                    Log.e("content", " start math ");
                }
//                MainActivity.getInstance().allCatagoryImage(start, level, context);
//                FilesDownload filesDownload = FilesDownload.getInstance(context, bothImg);
//                for (int i = 0; i < Global.URLS.size(); i++) {
//                    filesDownload.addUrl(Global.IMAGE_URL + Global.URLS.get(i));
//
//                }
//                FilesDownload.getInstance(context, "").start();
                GameActivity.getInstance().download();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void dialogShowWithWordArray(final int pos) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_show_with_word_text);
        RelativeLayout changeColor2 = (RelativeLayout) dialog.findViewById(R.id.dia_relativeLayout2);
        final TextView txt1 = (TextView) dialog.findViewById(R.id.txtOne);
        TextView txt2 = (TextView) dialog.findViewById(R.id.txtTwo);
        TextView txt3 = (TextView) dialog.findViewById(R.id.txtThree);
        TextView txt4 = (TextView) dialog.findViewById(R.id.txtFour);
        ImageView img1 = (ImageView) dialog.findViewById(R.id.imgOne);
        ImageView img2 = (ImageView) dialog.findViewById(R.id.imgTwo);
        ImageView img3 = (ImageView) dialog.findViewById(R.id.imgThree);
        Utils.setFont(context, "carterone", txt1, txt2, txt3, txt4);
        ImageView imgSound = (ImageView) dialog.findViewById(R.id.imgSoundOne);
        ImageView imgBack = (ImageView) dialog.findViewById(R.id.imgBackOne);
        ImageView imgForward = (ImageView) dialog.findViewById(R.id.imgForward1);
        txt1.setText(textArrayList.get(pos).getTxt());

        sounds = MainActivity.sounds;
        mainSounds = MainActivity.sounds;
        imageWords = MainActivity.image;
        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.PlaySound(mainSounds + File.separator + mContent.getAud());
            }
        });


        imgForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.GAME_INDEX_POSITION >= textArrayList.size() - 1) {
//                    Utils.toastMassage(context, "level finish");
                    GameActivity.getInstance().getIsSaveDataFromDb(Global.levelId, Global.subLevelId);
                    savePoint(textArrayList.size() - 1);
                    GameLogic.getInstance(context).isSavePoint();
                    dialogShowForLevelClear(textArrayList.size());
                    played();
                    if (Global.levelId == 1) {
                        GameActivity.getInstance().txtTotalPoint.setText(Utils.convertNum(Global.totalPoint + ""));
                    }
                    if (Global.levelId == 2) {
                        GameActivity.getInstance().txtTotalPoint.setText(Utils.convertNum(Global.totalPoint + ""));
                    }
                    if (Global.levelId == 3) {
                        GameActivity.getInstance().txtTotalPoint.setText(Global.totalPoint + "");
                    }
                    if (Global.levelId == 4) {
                        GameActivity.getInstance().txtTotalPoint.setText(Global.totalPoint + "");
                    }

                    countPoint = 0;

                    if (Global.SUB_INDEX_POSITION >= SubLevelActivity.mSubLevels.size() - 1) {

//                        Utils.toastMassage(context, "no more level");
                        dialog.dismiss();
                        return;

                    } else {

                        DatabaseHelper db = new DatabaseHelper(context);
                        MLock lock = new MLock();
                        lock = db.getLocalData(Global.levelId, SubLevelActivity.mSubLevels.get(Global.SUB_INDEX_POSITION + 1).getLid());
                        lock.setLevel_id(Global.levelId);
                        lock.setSub_level_id(SubLevelActivity.mSubLevels.get(Global.SUB_INDEX_POSITION + 1).getLid());
                        Log.e("LOGIC", "sid:" + lock.getSub_level_id());
                        lock.setUnlockNextLevel(1);
                        db.addLockData(lock);
                    }


//                    Collections.shuffle(textArrayList);
                    dialog.dismiss();

                } else {
                    countPoint++;
                    Global.GAME_INDEX_POSITION = Global.GAME_INDEX_POSITION + 1;

                    mContent = textArrayList.get(Global.GAME_INDEX_POSITION);
                    mContent.setWords(db.getAllWordsData(mContent.getMid()));
                    dialogShowWithWordArray(Global.GAME_INDEX_POSITION);
//                    txt1.setText(textArrayList.get(pos).getTxt());
                    dialog.dismiss();
                }
            }
        });
        Log.e("TEST", "s:" + mContent.getWords().size());
        if (Global.levelId == 1) {
            Utils.changeUIcolor(context, Global.uriBangla, changeColor2);
        }
        if (Global.levelId == 2) {
            Utils.changeUIcolor(context, Global.uriOngko, changeColor2);
        }
        if (Global.levelId == 3) {
            Utils.changeUIcolor(context, Global.uriEnglish, changeColor2);
        }
        if (Global.levelId == 4) {
            Utils.changeUIcolor(context, Global.uriMath, changeColor2);
        }
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.GAME_INDEX_POSITION <= 0) {
                    Utils.toastMassage(context, "level finish");

                    dialog.dismiss();

                } else {
                    Global.GAME_INDEX_POSITION = Global.GAME_INDEX_POSITION - 1;

                    mContent = textArrayList.get(Global.GAME_INDEX_POSITION);
                    mContent.setWords(db.getAllWordsData(mContent.getMid()));
                    dialogShowWithWordArray(Global.GAME_INDEX_POSITION);
//                    txt1.setText(textArrayList.get(pos).getTxt());
                    Utils.toastMassage(context, "Position");
                    dialog.dismiss();
                }
//
            }
        });

        if (mContent.getWords().size() == 4) {
//            txt1.setText(mContent.getWords().get(0).getWword());
            txt2.setText(mContent.getWords().get(0).getWword());
            txt3.setText(mContent.getWords().get(1).getWword());
            txt4.setText(mContent.getWords().get(2).getWword());

            Bitmap bmp = BitmapFactory.decodeFile(imageWords + File.separator + mContent.getWords().get(0).getWimg());
            Bitmap bmp2 = BitmapFactory.decodeFile(imageWords + File.separator + mContent.getWords().get(1).getWimg());
            Bitmap bmp3 = BitmapFactory.decodeFile(imageWords + File.separator + mContent.getWords().get(2).getWimg());
            img1.setImageBitmap(bmp);
            img2.setImageBitmap(bmp2);
            img3.setImageBitmap(bmp3);

            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Utils.PlaySound(sounds + File.separator + mContent.getWords().get(0).getWsound());
                }
            });
            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Utils.PlaySound(sounds + File.separator + mContent.getWords().get(1).getWsound());

                }
            });
            img3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Utils.PlaySound(sounds + File.separator + mContent.getWords().get(2).getWsound());

                }
            });

//            String url = Global.IMAGE_URL + mContent.getWords().get(0).getWimg();
//            String url2 = Global.IMAGE_URL + mContent.getWords().get(1).getWimg();
//            String url3 = Global.IMAGE_URL + mContent.getWords().get(2).getWimg();
//            Picasso.with(context)
//                    .load(url)
//                    .into(img1);
//            Picasso.with(context)
//                    .load(url2)
//                    .into(img2);
//            Picasso.with(context)
//                    .load(url3)
//                    .into(img3);
        } else if (mContent.getWords().size() == 3) {
            txt2.setText(mContent.getWords().get(0).getWword());
            txt3.setText(mContent.getWords().get(1).getWword());
            txt4.setText(mContent.getWords().get(2).getWword());
            Bitmap bmp = BitmapFactory.decodeFile(imageWords + File.separator + mContent.getWords().get(0).getWimg());
            Bitmap bmp2 = BitmapFactory.decodeFile(imageWords + File.separator + mContent.getWords().get(1).getWimg());
            Bitmap bmp3 = BitmapFactory.decodeFile(imageWords + File.separator + mContent.getWords().get(2).getWimg());
            img1.setImageBitmap(bmp);
            img2.setImageBitmap(bmp2);
            img3.setImageBitmap(bmp3);

            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Utils.PlaySound(sounds + File.separator + mContent.getWords().get(0).getWsound());
                }
            });
            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Utils.PlaySound(sounds + File.separator + mContent.getWords().get(1).getWsound());

                }
            });
            img3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Utils.PlaySound(sounds + File.separator + mContent.getWords().get(2).getWsound());

                }
            });
        } else if (mContent.getWords().size() == 2) {
            txt2.setText(mContent.getWords().get(0).getWword());
            txt3.setText(mContent.getWords().get(1).getWword());
            Bitmap bmp = BitmapFactory.decodeFile(imageWords + File.separator + mContent.getWords().get(0).getWimg());
            Bitmap bmp2 = BitmapFactory.decodeFile(imageWords + File.separator + mContent.getWords().get(1).getWimg());
            img1.setImageBitmap(bmp);
            img2.setImageBitmap(bmp2);

            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Utils.PlaySound(sounds + File.separator + mContent.getWords().get(0).getWsound());
                }
            });
            img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Utils.PlaySound(sounds + File.separator + mContent.getWords().get(1).getWsound());

                }
            });
        } else if (mContent.getWords().size() == 1) {
            txt2.setText(mContent.getWords().get(0).getWword());
            Bitmap bmp = BitmapFactory.decodeFile(imageWords + File.separator + mContent.getWords().get(0).getWimg());
            img1.setImageBitmap(bmp);

            img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Utils.PlaySound(sounds + File.separator + mContent.getWords().get(0).getWsound());
                }
            });

        }

        dialog.show();

    }
    public void dialogShowForLevelClear(final int listSize) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_level_cleared);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LinearLayout changeColor = (LinearLayout) dialog.findViewById(R.id.dia_LenearLayout);
        TextView txtClear = (TextView) dialog.findViewById(R.id.dia_level_clear);
        final ImageView txtPoint = (ImageView) dialog.findViewById(R.id.txtLevelPoint);
//        final TextView txtBestPoint = (TextView) dialog.findViewById(R.id.txtLevelBestPoint);
        final TextView txtScore = (TextView) dialog.findViewById(R.id.txtLevelScore);
        ImageView imgLevelMenu = (ImageView) dialog.findViewById(R.id.imgLevelMenu);
        ImageView imgFacebook = (ImageView) dialog.findViewById(R.id.imgFacebook);


        Utils.setFont(context, "carterone", txtClear);
        imgLevelMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                            Intent intent = new Intent(context, SubLevelActivity.class);
//                            intent.putExtra("id", Global.levelId);
//                            intent.putExtra("name", Global.levelName);
//                            context.startActivity(intent);
                ((Activity) context).finish();
                dialog.dismiss();


                Utils.toastMassage(context, "Return Game Level");
            }
        });
        if (Global.levelId == 1) {
            Utils.setFont(context, "BenSenHandwriting", txtScore);
            Utils.changeUIcolor(context, Global.uriBangla, changeColor);
        } else if (Global.levelId == 2) {
            Utils.setFont(context, "BenSenHandwriting", txtScore);
            Utils.changeUIcolor(context, Global.uriOngko, changeColor);
        } else if (Global.levelId == 3) {
            Utils.setFont(context, "carterone", txtScore);
            Utils.changeUIcolor(context, Global.uriEnglish, changeColor);
        } else if (Global.levelId == 4) {
            Utils.setFont(context, "carterone", txtScore);
            Utils.changeUIcolor(context, Global.uriMath, changeColor);
        }

        ImageView imgReload = (ImageView) dialog.findViewById(R.id.btnLevelReload);
        imgReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameActivity.getInstance().getIsSaveDataFromDb(Global.levelId, Global.subLevelId);
//                GameLogic.getInstance(context).resetList(listSize);
                dialog.dismiss();
            }


        });
        ImageView imgNextLevel = (ImageView) dialog.findViewById(R.id.imgLevelForward);
        imgNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Global.SUB_INDEX_POSITION >= mSubLevels.size() - 1) {
                    Utils.toastMassage(context, "Level Finished ");

                    return;

                } else {
                    mSubLevels.get(Global.SUB_INDEX_POSITION).setColor(1);
                    Global.SUB_INDEX_POSITION = Global.SUB_INDEX_POSITION + 1;

//                    Global.CONTENT=Global.CONTENT;
                    mSubLevels.get(Global.SUB_INDEX_POSITION).setUnlockNextLevel(1);
                    Global.subLevelId = mSubLevels.get(Global.SUB_INDEX_POSITION).getLid();
                    Global.logic = mSubLevels.get(Global.SUB_INDEX_POSITION).getLogic();
                    Global.CONTENT = mSubLevels.get(Global.SUB_INDEX_POSITION).getContent();
                    GameActivity.getInstance().getIsSaveDataFromDb(Global.levelId, Global.subLevelId);
                    GameActivity.getInstance().refresh(Global.SUB_INDEX_POSITION, Global.CONTENT);
                    String start = DialogSoundOnOff.getPREF(Global.levelId + "");
                    String maxContent = Utils.getPREF(Global.levelId + "");
                    int s = Integer.valueOf(start);
                    int m = Integer.valueOf(maxContent);
                    Log.e("content", " start " + s);
                    Log.e("content", " max " + m);
                    Log.e("content", " present " + Global.CONTENT);
                    if (Global.CONTENT > m) {
                        dialog.dismiss();
                        dialogShow(s, 0, Global.levelId);
                        return;
                    }
                }
                dialog.dismiss();
            }
        });
        imgFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/apple.fruit"));
                context.startActivity(browserIntent);
                dialog.dismiss();
            }
        });

        if (Global.isSavePoint == 0) {
            if (Global.levelId == 1) {
                txtScore.setText("Score :  " + Utils.convertNum(present + ""));
            }
            if (Global.levelId == 2) {
                txtScore.setText("Score :  " + Utils.convertNum(present + ""));
            }
            if (Global.levelId == 3) {
                txtScore.setText("Score :  " + present + "");
            }
            if (Global.levelId == 4) {
                txtScore.setText("Score :  " + present + "");
            }
//        txtScore.setText("Score :  " + present + "");
            if (present == 50) {
                txtPoint.setImageResource(R.drawable.star_1);
            } else if (present == 75) {
                txtPoint.setImageResource(R.drawable.star_2);
            } else if (present == 100) {
                txtPoint.setImageResource(R.drawable.star_3);
            }
        }


//        else txtPoint.setText(Utils.getIntToStar(0));
        dialog.show();
    }
    private void dialogShowWithWordsList() {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_show_text);
        RelativeLayout changeColor2 = (RelativeLayout) dialog.findViewById(R.id.relLayout);

        TextView txt1 = (TextView) dialog.findViewById(R.id.txt1);
        TextView txt2 = (TextView) dialog.findViewById(R.id.txt2);
        TextView txt3 = (TextView) dialog.findViewById(R.id.txt3);
        TextView txt4 = (TextView) dialog.findViewById(R.id.txt4);
        ImageView img1 = (ImageView) dialog.findViewById(R.id.img1);
        ImageView img2 = (ImageView) dialog.findViewById(R.id.img2);
        ImageView img3 = (ImageView) dialog.findViewById(R.id.img3);
        ImageView img4 = (ImageView) dialog.findViewById(R.id.img4);
        Utils.setFont(context, "carterone", txt1, txt2, txt3, txt4);
        ImageView imgBack = (ImageView) dialog.findViewById(R.id.imgBack);
        ImageView imgClose = (ImageView) dialog.findViewById(R.id.imgClose);
        if (Global.levelId == 1) {
            Utils.changeUIcolor(context, Global.uriBangla, changeColor2);
        } else if (Global.levelId == 2) {
            Utils.changeUIcolor(context, Global.uriOngko, changeColor2);
        } else if (Global.levelId == 3) {
            Utils.changeUIcolor(context, Global.uriEnglish, changeColor2);
        }
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.GAME_INDEX_POSITION <= 0) {
                    Utils.toastMassage(context, "level finish");

                    dialog.dismiss();

                } else {
                    Global.GAME_INDEX_POSITION = Global.GAME_INDEX_POSITION - 1;

                    mContent = textArrayList.get(Global.GAME_INDEX_POSITION);

                    mContent.setWords(db.getAllWordsData(mContent.getMid()));

                    dialogShowWithWordsList();
                    Utils.toastMassage(context, "Position");
                    dialog.dismiss();
                }
//
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Log.e("TEST", "s:" + mContent.getWords().size());
        if (mContent.getWords().size() == 4) {
            txt1.setText(mContent.getWords().get(0).getWword());
            txt2.setText(mContent.getWords().get(1).getWword());
            txt3.setText(mContent.getWords().get(2).getWword());
            txt4.setText(mContent.getWords().get(3).getWword());

            String url = Global.IMAGE_URL + mContent.getWords().get(0).getWimg();
            String url2 = Global.IMAGE_URL + mContent.getWords().get(1).getWimg();
            String url3 = Global.IMAGE_URL + mContent.getWords().get(2).getWimg();
            String url4 = Global.IMAGE_URL + mContent.getWords().get(3).getWimg();
            Picasso.with(context)
                    .load(url)
                    .into(img1);
            Picasso.with(context)
                    .load(url2)
                    .into(img2);
            Picasso.with(context)
                    .load(url3)
                    .into(img3);
            Picasso.with(context)
                    .load(url4)
                    .into(img4);

        } else if (mContent.getWords().size() == 3) {
            txt1.setText(mContent.getWords().get(0).getWword());
            txt2.setText(mContent.getWords().get(1).getWword());
            txt3.setText(mContent.getWords().get(2).getWword());
            String url = Global.IMAGE_URL + mContent.getWords().get(0).getWimg();
            String url2 = Global.IMAGE_URL + mContent.getWords().get(1).getWimg();
            String url3 = Global.IMAGE_URL + mContent.getWords().get(2).getWimg();
            Picasso.with(context)
                    .load(url)
                    .into(img1);
            Picasso.with(context)
                    .load(url2)
                    .into(img2);
            Picasso.with(context)
                    .load(url3)
                    .into(img3);
        } else if (mContent.getWords().size() == 2) {
            txt1.setText(mContent.getWords().get(0).getWword());
            txt2.setText(mContent.getWords().get(1).getWword());
            String url = Global.IMAGE_URL + mContent.getWords().get(0).getWimg();
            String url2 = Global.IMAGE_URL + mContent.getWords().get(1).getWimg();
            Picasso.with(context)
                    .load(url)
                    .into(img1);
            Picasso.with(context)
                    .load(url2)
                    .into(img2);
        } else if (mContent.getWords().size() == 1) {
            txt1.setText(mContent.getWords().get(0).getWword());
            String url = Global.IMAGE_URL + mContent.getWords().get(0).getWimg();
            Log.e("imgae", "url is" + url);
            Picasso.with(context)
                    .load(url)
                    .into(img1);
//            img1.setImageResource(mContent.getWords().get(0).getWimg());


        }
        dialog.show();
    }

    public void played() {
        MLock mLock = new MLock();
        mLock = db.getLocalData(Global.levelId, Global.subLevelId);
        mLock.setLevel_id(Global.levelId);
        mLock.setSub_level_id(Global.subLevelId);
        mLock.setColor(1);
        db.addLockData(mLock);
    }

    public void savePoint(int listSize) {
        present = pointCount(listSize);
        if (Global.isSavePoint == 0) {
            present = pointCount(listSize);
            Global.totalPoint = Global.totalPoint + present;
            GameLogic.getInstance(context).saveDb();
            if (present > Utils.bestPoint) {
                Utils.bestPoint = present;
                GameLogic.getInstance(context).saveDb();
            }
        }

//        addDb();


    }

    private int pointCount(int listSize) {
        int point = 50;

        if (countPoint == listSize) {
            point = 100;
        } else if (countPoint < listSize && countPoint > 1) {
            point = 75;
        }
        Log.e("pint", "point is" + point);
        return point;
    }
}
