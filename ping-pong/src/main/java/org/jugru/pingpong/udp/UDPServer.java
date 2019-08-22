package org.jugru.pingpong.udp;

import lombok.SneakyThrows;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer implements Closeable {
    private DatagramSocket socket;


    @SneakyThrows
    public UDPServer(int port) {
        socket = new DatagramSocket(port);
    }

    @SneakyThrows
    public String receive() {
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String data = new String(packet.getData(), 0, packet.getLength());
        return data;
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
