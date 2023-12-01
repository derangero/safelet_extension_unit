package com.example.myapplication3;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class SurfaceTestView extends SurfaceView implements SurfaceHolder.Callback {

    private Context context;
    private MediaPlayer mp;
    Uri mediaPathUri;

    public SurfaceTestView(Context pContext, MediaPlayer pMp, Uri pMediaPathUri) {
        super(pContext);
        context = pContext;
        mp = pMp;
        mediaPathUri = pMediaPathUri;
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO 自動生成されたメソッド・スタブ
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mp = new MediaPlayer();
            mp.setDataSource(context, mediaPathUri);
            mp.setDisplay(holder);
            mp.prepare();
            mp.start();
        } catch (IllegalArgumentException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mp != null){
            mp.release();
            mp = null;
        }
    };
}