package tdc.edu.vn.multimedia.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import tdc.edu.vn.multimedia.R;

public class Player extends LinearLayout {
    private ImageView btnNext, btnPause, btnPlay, btnPrev, btnStop;
    private ViewGroup playerLayout;
    private PlayerDelegation delegate;

    private OnClickListener onClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.isSelected()){
                v.setSelected(false);
            } else {
                v.setSelected(true);
                int count = playerLayout.getChildCount();
                for (int i = 0; i < count; ++i){
                    if (playerLayout.getChildAt(i).getId() != v.getId()){
                        playerLayout.getChildAt(i).setSelected(false);
                    }
                }
            }

            //Processing playerDelegation
            if(delegate == null){
                Toast.makeText(getContext(), "You must set delegate before!",Toast.LENGTH_LONG).show();
            } else {
                switch (v.getId()){
                    case R.id.btnNext:
                        delegate.next();
                        break;
                    case R.id.btnPause:
                        delegate.pause();
                        break;
                    case R.id.btnPlay:
                        delegate.play();
                        break;
                    case R.id.btnStop:
                        delegate.stop();
                        break;
                    case R.id.btnPrev:
                        delegate.prev();
                        break;
                }
            }
        }
    };

    public Player(Context context) {
        super(context);
        initialzation();
    }

    public Player(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialzation();
    }

    public Player(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialzation();
    }

    //Initialzation of the control
    private void initialzation(){
        inflate(getContext(), R.layout.player_layout, this);
        playerLayout = (ViewGroup) getChildAt(0);

        btnNext = playerLayout.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(onClick);
        btnPause = playerLayout.findViewById(R.id.btnPause);
        btnPause.setOnClickListener(onClick);
        btnPlay = playerLayout.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(onClick);
        btnPrev = playerLayout.findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(onClick);
        btnStop = playerLayout.findViewById(R.id.btnStop);
        btnStop.setOnClickListener(onClick);
    }

    public void setDelegate(PlayerDelegation delegate) {
        this.delegate = delegate;
    }
}
