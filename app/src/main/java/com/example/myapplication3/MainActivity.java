package com.example.myapplication3;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowInsets;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import java.io.IOException;

public class MainActivity extends Activity implements SurfaceHolder.Callback{

    private UDPReceptionController udpReceptionController;
    private UDPSendController udpSendController;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private int mediaCurrentPos;
    private MediaPlayer mp;
    private SurfaceHolder holder;
    private SurfaceView mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 動画へのURLを取得
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sample1);
        //getWindow().setFormat(PixelFormat.TRANSPARENT);

        View decorView = getWindow().getDecorView();
        // ナビゲーションバーを非表示にする
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            decorView.getWindowInsetsController().hide(
                    WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars()
            );
        }
        // bean生成
        MainActivityBean mainActivityBean = new MainActivityBean();

        mainActivityBean.setSurfaceView(findViewById(R.id.surface));

//        SurfaceHolder surfaceHolder = mainActivityBean.getSurfaceView().getHolder();
//        surfaceHolder.addCallback(this);
//        // 動画をコントロールするためMediaController
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(findViewById(R.id.video_1));
//        mediaController.setVisibility(View.INVISIBLE);
//        mediaController.hide();

        mainActivityBean.setIdleVideoView(findViewById(R.id.video_1));
        String filePath = "android.resource://"+getPackageName()+"/"+R.raw.sample1;
        mainActivityBean.getIdleVideoView().setVideoPath(filePath);
        mainActivityBean.getIdleVideoView().setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mainActivityBean.getIdleVideoView().start();
            }
        });
        mainActivityBean.setInitialView(findViewById(R.id.image_initial));
        mainActivityBean.setAnnounceWaitingView(findViewById(R.id.image_announce_waiting));
        mainActivityBean.setNotUserActionByFirstView(findViewById(R.id.image_not_user_action_by_first));
        mainActivityBean.setNotUserActionBySecondView(findViewById(R.id.image_not_user_action_by_second));
        mainActivityBean.setTouchButton(findViewById(R.id.button_touch));

        mainActivityBean.getTouchButton().setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        switch (mainActivityBean.getDisplayMode()) {
                        case IDLE:
                        case ANNOUNCE_WAITING:
                            break;
                        case NOT_USER_ACTION_BY_FIRST:
                            case NOT_USER_ACTION_BY_LAST:
                            new UDPSendController(mainActivityBean, "check").start();
                            new UDPReceptionController(mainActivityBean, mainHandler).start();
                            break;
                        default:
                            break;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //離したとき
                }
                // trueにすると他のリスナーが呼ばれない
                return false;
            }
        });
        new UDPReceptionController(mainActivityBean, mainHandler).start();

        //new PollingController(mainActivityBean, mainHandler).start();
    }

    public void pause(){
        //NOT videoview.pause(); Needn't save Stop position
        if (mp != null){
            mediaCurrentPos = mp.getCurrentPosition();
            mp.pause();
        }
    }

    public void resume(){
        //NOT videoview.resume();
        if (mp != null && mediaCurrentPos != 0){
            mp.seekTo(mediaCurrentPos);
            mp.start(); //Video will begin where it stopped
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    private void connect(String ip, int port){
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Uri mediaPathUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sample1);
        try {
            mp = new MediaPlayer();
            mp.setDataSource(this, mediaPathUri);
            mp.setDisplay(holder);
            mp.setLooping(true);
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
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if(mp != null){
            mp.release();
            mp = null;
        }
    }
}
