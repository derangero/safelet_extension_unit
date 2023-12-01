package com.example.myapplication3;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPUtil {

    public static DatagramSocket createSocket() {
        try {
            DatagramSocket socket = new DatagramSocket(null);
            socket.setReuseAddress(true);
            socket.bind(new InetSocketAddress(UDPConstants.UDP_PORT));

            return socket;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
