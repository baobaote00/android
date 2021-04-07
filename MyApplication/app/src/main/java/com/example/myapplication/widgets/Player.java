package com.example.myapplication.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class Player extends LinearLayout {
    private ImageView btnNext,btnPre,btnPause,btnStop,btnPlay;
    private ViewGroup playerLayout;

    private final OnClickListener onClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.isSelected()) {
                v.setSelected(false);
            } else {
                v.setSelected(true);
                int count = playerLayout.getChildCount();
                for (int i = 0; i < count; i++) {
                    if (playerLayout.getChildAt(i).getId() != v.getId()) {
                        playerLayout.getChildAt(i).setSelected(false);
                    }
                }
            }
        }
    };

    public Player(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public Player(Context context) {
        super(context);
        init();
    }
    public Player(Context context,@Nullable AttributeSet attrs) {
        super(context,attrs);
        init();
    }
    private void init(){
        inflate(getContext(), R.layout.my_widgets,this);
        playerLayout = (ViewGroup) getChildAt(0);
        btnNext = playerLayout.findViewById(R.id.btnNext);
        btnPause = playerLayout.findViewById(R.id.btnPause);
        btnPlay = playerLayout.findViewById(R.id.btnPlay);
        btnPre = playerLayout.findViewById(R.id.btnPre);
        btnStop = playerLayout.findViewById(R.id.btnStop);
        btnNext.setOnClickListener(onClick);
        btnPause.setOnClickListener(onClick);
        btnPlay.setOnClickListener(onClick);
        btnPre.setOnClickListener(onClick);
        btnStop.setOnClickListener(onClick);
    }
}
