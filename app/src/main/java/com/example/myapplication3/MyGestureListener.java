package com.example.myapplication3;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
    //ダブルタップイベント
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        //処理あれこれ
        return true;
    }
}
