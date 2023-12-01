package com.example.myapplication3;
import android.os.Handler;
import android.view.View;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import constants.DisplayMode;

public class UDPConstants {
    public static final int UDP_PORT = 10000;
    public static final int UDP_BUFFER_SIZE = 8192;
    //public static final String SEND_IP = "192.168.10.101";
    public static final String SEND_IP = "192.168.2.80";
}
