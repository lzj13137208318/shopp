package com.example.shopping.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomViewGroup extends ScrollView {

    public CustomViewGroup(@NonNull Context context) {
        super(context);
    }

    public CustomViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


}
