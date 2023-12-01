package com.example.myapplication3;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.VideoView;

import constants.DisplayMode;

public class MainActivityBean {

    private DisplayMode displayMode;
    private VideoView idleVideoView;
    private SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    private View initialView;
    private View announceWaitingView;
    private View notUserActionByFirstView;
    private View notUserActionBySecondView;
    private View touchButton;
    private long elapsed;
    public MainActivityBean() {
        displayMode = DisplayMode.NONE;
        elapsed = 0L;
    }

    public SurfaceView getSurfaceView() {
        return surfaceView;
    }

    public void setSurfaceView(SurfaceView surfaceView) {
        this.surfaceView = surfaceView;
    }

    public DisplayMode getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(DisplayMode displayMode) {
        this.displayMode = displayMode;
    }

    public VideoView getIdleVideoView() {
        return idleVideoView;
    }

    public void setIdleVideoView(VideoView idleVideoView) {
        this.idleVideoView = idleVideoView;
    }

    public View getInitialView() {
        return initialView;
    }

    public void setInitialView(View initialView) {
        this.initialView = initialView;
    }

    public View getAnnounceWaitingView() {
        return announceWaitingView;
    }

    public void setAnnounceWaitingView(View announceWaitingView) {
        this.announceWaitingView = announceWaitingView;
    }

    public View getNotUserActionByFirstView() {
        return notUserActionByFirstView;
    }

    public void setNotUserActionByFirstView(View notUserActionByFirstView) {
        this.notUserActionByFirstView = notUserActionByFirstView;
    }

    public View getNotUserActionBySecondView() {
        return notUserActionBySecondView;
    }

    public void setNotUserActionBySecondView(View notUserActionBySecondView) {
        this.notUserActionBySecondView = notUserActionBySecondView;
    }

    public View getTouchButton() {
        return touchButton;
    }

    public void setTouchButton(View touchButton) {
        this.touchButton = touchButton;
    }

    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }
}
