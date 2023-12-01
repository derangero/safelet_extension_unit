package com.example.myapplication3;

import android.os.Handler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import constants.DisplayMode;

public class PollingController extends Thread {
    private MainActivityBean mainActivityBean;
    private Handler mainHandler;
    private String sendCommand;
    public PollingController(MainActivityBean mainActivityBean, Handler mainHandler){
        this.mainActivityBean = mainActivityBean;
        this.mainHandler = mainHandler;
    }

    public void run() {
        System.out.println("Palling を開始します。");
        long base = System.currentTimeMillis();
        while(true) {
            try {
                if (!DisplayMode.NONE.equals(mainActivityBean.getDisplayMode())) {
                    mainActivityBean.setElapsed(
                        System.currentTimeMillis() - base
                    );
                }
                if (mainActivityBean.getElapsed() >= 6000L) {
                    new UDPSendController(mainActivityBean, "s00").start();
                    new UDPReceptionController(mainActivityBean, mainHandler).start();
                    mainActivityBean.setElapsed(0L);
                    base = System.currentTimeMillis();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getSendCommand() {
        return sendCommand;
    }

    public void setSendCommand(String sendCommand) {
        this.sendCommand = sendCommand;
    }

}
