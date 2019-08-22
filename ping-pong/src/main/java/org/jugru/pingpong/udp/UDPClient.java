package org.jugru.pingpong.udp;

import lombok.SneakyThrows;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient implements Closeable {
    private DatagramSocket socket;
    private int port;


    @SneakyThrows
    public UDPClient(int port) {
        socket = new DatagramSocket();
        this.port = port;
    }

    @SneakyThrows
    public void send(String data) {
        byte[] buf = data.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), port);
        socket.send(packet);
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
