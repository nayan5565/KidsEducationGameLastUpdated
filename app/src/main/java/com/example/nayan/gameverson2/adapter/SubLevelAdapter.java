package com.example.nayan.gameverson2.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nayan.gameverson2.R;
import com.example.nayan.gameverson2.activity.GameActivity;
import com.example.nayan.gameverson2.activity.MainActivity;
import com.example.nayan.gameverson2.activity.SubLevelActivity;
import com.example.nayan.gameverson2.model.MColor;
import com.example.nayan.gameverson2.model.MLock;
import com.example.nayan.gameverson2.model.MSubLevel;
import com.example.nayan.gameverson2.tools.DatabaseHelper;
import com.example.nayan.gameverson2.tools.DialogSoundOnOff;
import com.example.nayan.gameverson2.tools.FilesDownload;
import com.example.nayan.gameverson2.tools.Global;
import com.example.nayan.gameverson2.tools.Utils;

import java.util.ArrayList;


/**
 * Created by NAYAN on 11/24/2016.
 */
public class SubLevelAdapter extends RecyclerView.Adapter<SubLevelAdapter.MyViewHolder> {
    int one;
    private ArrayList<MSubLevel> mSubLevels;
    private MSubLevel mSubLevel = new MSubLevel();
    private MLock mLock = new MLock();
    private MColor mColor = new MColor();
    private Context context;
    private LayoutInflater inflater;
    private DatabaseHelper db;
    private int count;
    private int subLevel;

    public SubLevelAdapter(Context context) {
        this.context = context;
        mSubLevels = new ArrayList<>();
        db = new DatabaseHelper(context);
        inflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<MSubLevel> mSubLevels) {
        this.mSubLevels = mSubLevels;

        Log.e("log", "setdata:" + mSubLevels.size());
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_image, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        mSubLevel = mSubLevels.get(position);
        holder.txtSubLevel.setText(mSubLevel.getName());
        mColor = db.getColor(Global.levelId, Global.subLevelId);

        if (mSubLevel.getBestPoint() == 100) {
            holder.imgStar.setImageResource(R.drawable.s_star_3);
        } else if (mSubLevel.getBestPoint() == 75) {
            holder.imgStar.setImageResource(R.drawable.s_star_2);
        } else if (mSubLevel.getBestPoint() == 50) {
            holder.imgStar.setImageResource(R.drawable.s_star_1);
        } else {
            holder.imgStar.setImageResource(R.drawable.s_star_4);
        }

        if (mSubLevel.getUnlockNextLevel() == 1) {
            holder.imgLock.setVisibility(View.GONE);
            holder.imgSub.setImageResource(R.drawable.sublevel_item_view);
        } else if (mSubLevel.getIsDownload() == 1) {
            holder.imgLock.setVisibility(View.VISIBLE);
            holder.imgSub.setImageResource(R.drawable.sublevel_item_view);
        } else if (mSubLevel.getColor() == 1) {
            holder.txtSubLevel.setTextColor(Color.RED);
        }else if (mSubLevel.getIsDownload()==0){
            holder.imgLock.setVisibility(View.VISIBLE);
            holder.imgSub.setImageResource(R.drawable.inactive_bg);
        }
        else {
//            if (mColor.getColor() == 1) {
//                holder.imgLock.setVisibility(View.VISIBLE);
//                holder.imgSub.setImageResource(R.drawable.sublevel_item_view);
//            }
            holder.imgLock.setVisibility(View.VISIBLE);
            holder.imgSub.setImageResource(R.drawable.inactive_bg);
        }


    }

    @Override
    public int getItemCount() {
        return mSubLevels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtSubLevel;
        ImageView imgLock, imgStar, imgSub;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtSubLevel = (TextView) itemView.findViewById(R.id.txtLevel);
            imgStar = (ImageView) itemView.findViewById(R.id.imgStar);
            imgLock = (ImageView) itemView.findViewById(R.id.imgLock);
            imgSub = (ImageView) itemView.findViewById(R.id.imgSub);
            if (Global.levelId == 1) {
                Utils.setFont(context, "BenSen", txtSubLevel);
            }
            if (Global.levelId == 2) {
                Utils.setFont(context, "BenSen", txtSubLevel);
            }
            if (Global.levelId == 3) {
                Utils.setFont(context, "skranjibold", txtSubLevel);
            }
            if (Global.levelId == 4) {
                Utils.setFont(context, "skranjibold", txtSubLevel);
            }

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.e("levelID", "idd  " + Global.levelId);

                    mSubLevel = mSubLevels.get(getAdapterPosition());

                    Utils.bestPoint = mSubLevel.getBestPoint();
                    if (mSubLevel.getUnlockNextLevel() == 1) {



                        String start = DialogSoundOnOff.getPREF( Global.levelId + "");
                        int s = Integer.valueOf(start);
                        String maxContent = Utils.getPREF( Global.levelId + "");
                        int m = Integer.valueOf(maxContent);
                        Log.e("content", " start " + s);
                        Log.e("content", " max " + m);


                        Intent intent = new Intent(context, GameActivity.class);
                        intent.putExtra("subLevelName", mSubLevel.getName());
                        intent.putExtra("index", getAdapterPosition());
                        intent.putExtra("Sid", mSubLevel.getLid());
                        intent.putExtra("content", mSubLevel.getContent());
//                        intent.putExtra("pop", mSubLevel.getIsPopUp());
                        intent.putExtra("content", mSubLevel.getContent());
                        intent.putExtra("SLogic", mSubLevel.getLogic());
                        intent.putExtra("parentLevelName", mSubLevel.getParentName());
                        if (mSubLevel.getContent() > m) {
//                            if (s == 0) {
//                                s = s + 6;
//                            }
                            dialogShow(s, getAdapterPosition(), Global.levelId);
                            notifyDataSetChanged();
                            return;
                        }
                        context.startActivity(intent);

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
                SubLevelActivity.getInstane().download();
                SubLevelActivity.getInstane().color();
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                mSubLevel = mSubLevels.get(pos);
//                if (mSubLevel.getUnlockNextLevel() == 1) {
//
//                    Intent intent = new Intent(context, GameActivity.class);
//                    intent.putExtra("subLevelName", mSubLevel.getName());
//                    intent.putExtra("index", pos);
//                    intent.putExtra("Sid", mSubLevel.getLid());
//                    intent.putExtra("content", mSubLevel.getContent());
////                        intent.putExtra("pop", mSubLevel.getIsPopUp());
//                    intent.putExtra("content", mSubLevel.getContent());
//                    intent.putExtra("SLogic", mSubLevel.getLogic());
//                    intent.putExtra("parentLevelName", mSubLevel.getParentName());
//                    context.startActivity(intent);
//
//                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
