package com.example.shopping.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.core.widget.NestedScrollView;

public class Myscrollview extends NestedScrollView {

    private boolean isNeedScroll = true;
    private float xDistance, yDistance, xLast, yLast;
    private int scaledTouchSlop;


    public Myscrollview(Context context) {
        super(context);
    }

    public Myscrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Myscrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                Log.e("SiberiaDante", "xDistance ：" + xDistance + "---yDistance:" + yDistance);
                return !(xDistance >= yDistance || yDistance < scaledTouchSlop) && isNeedScroll;

        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setNeedScroll(boolean b) {
        this.isNeedScroll = b;
    }
}
