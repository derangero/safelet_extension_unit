package com.example.myapplication3;
import android.os.Handler;
import android.view.View;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

import constants.DisplayMode;

public class UDPSendController extends Thread {
    private MainActivityBean mainActivityBean;
    private DatagramSocket socket;
    private String sendCommand;
    private boolean isRunning;
    public UDPSendController(MainActivityBean mainActivityBean, String command){
        this.mainActivityBean = mainActivityBean;
        this.sendCommand = command;
        this.isRunning = true;
    }

    public void run() {
        System.out.println("UDP通信_送信を開始します。");
        this.socket = UDPUtil.createSocket();
        while(isRunning) {
            try {
                if (this.sendCommand.equals("check") || this.sendCommand.equals("s00")) {
                    send();
                    isRunning = false;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void send() throws IOException {
        byte[] buff = this.sendCommand.getBytes();

        DatagramPacket packet = new DatagramPacket(buff, buff.length,
                InetAddress.getByName(UDPConstants.SEND_IP), UDPConstants.UDP_PORT);

        this.socket.send(packet);
        this.socket.close();
    }

    public String getSendCommand() {
        return sendCommand;
    }

    public void setSendCommand(String sendCommand) {
        this.sendCommand = sendCommand;
    }
}
