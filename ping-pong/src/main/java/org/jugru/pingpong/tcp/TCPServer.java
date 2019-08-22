package org.jugru.pingpong.tcp;

import lombok.SneakyThrows;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;

public class TCPServer implements Closeable {
    final private ServerSocket socket;

    @SneakyThrows
    public TCPServer(int port) {
        this.socket = new ServerSocket(port);
    }

    @SneakyThrows
    public TCPClient accept() {
        return new TCPClient(socket.accept());
    }


    @Override
    public void close() throws IOException {
        socket.close();
    }
}
