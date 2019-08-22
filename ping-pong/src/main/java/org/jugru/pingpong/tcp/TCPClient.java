package org.jugru.pingpong.tcp;

import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;

public class TCPClient implements Closeable {

    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    @SneakyThrows
    public TCPClient(int port) {
        this.socket = new Socket("localhost", port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    }

    @SneakyThrows
    public TCPClient(Socket socket) {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    }

    public void send(String message){
        out.println(message);
    }

    @SneakyThrows
    public String receive(){
        return in.readLine();
    }


    @Override
    public void close() throws IOException {
        socket.close();
        in.close();
        out.close();
    }
}
