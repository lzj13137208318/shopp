package com.example.shopping.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.recyclerview.widget.RecyclerView;

public class Youscrollview extends RecyclerView {

    public Youscrollview(Context context) {
        super(context);
    }

    public Youscrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() ==MotionEvent.ACTION_DOWN){
            return false;
        }
        return super.onTouchEvent(e);

    }
}
