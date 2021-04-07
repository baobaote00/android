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

    }
}
