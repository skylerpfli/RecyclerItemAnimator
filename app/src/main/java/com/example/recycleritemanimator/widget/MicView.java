package com.example.recycleritemanimator.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycleritemanimator.R;

/**
 * @author lipengfei
 * @date 2019/12/13
 * @email 1219742019@qq.com
 * @description 底部麦克风小图标
 */
public class MicView extends FrameLayout {
    public MicView(Context context) {
        super(context);
        initView(context);
    }

    public MicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private ImageView mRecRed;
    private ImageView mRecBlack;
    private TextView mRecTxt;

    private void initView(Context context) {
        View.inflate(context, R.layout.view_mic, this);
        mRecBlack = (ImageView) findViewById(R.id.rec_black);
        mRecRed = (ImageView) findViewById(R.id.rec_red);
        mRecTxt = (TextView) findViewById(R.id.rec_txt);
    }

    // 标准状态
    public void onPrepare() {
        mRecRed.clearAnimation();
        mRecRed.setVisibility(INVISIBLE);
        mRecBlack.setVisibility(VISIBLE);
        mRecTxt.setVisibility(INVISIBLE);
    }

    // 录音中
    public void onRecord() {
        mRecRed.setVisibility(VISIBLE);
        mRecBlack.setVisibility(VISIBLE);
        mRecTxt.setVisibility(INVISIBLE);
        ScaleAnimation animation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setDuration(500);
        animation.setRepeatCount(-1);
        mRecRed.startAnimation(animation);
    }

    // 处理中
    public void onProcess(CharSequence sequence) {
        mRecRed.clearAnimation();
        mRecRed.setVisibility(INVISIBLE);
        mRecBlack.setVisibility(INVISIBLE);
        mRecTxt.setVisibility(VISIBLE);
        setText(sequence);
    }

    private void setText(CharSequence sequence) {
        mRecTxt.setText(sequence);
    }
}
