package org.jugru.proxy;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
class ClientToServer implements Runnable {
    private final InputStream streamFromClient;
    private final OutputStream streamToServer;


    public ClientToServer(InputStream streamFromClient, OutputStream streamToServer) {
        this.streamFromClient = streamFromClient;
        this.streamToServer = streamToServer;
    }

    @Override
    public void run() {
        int bytesRead;
        final byte[] request = new byte[(int) Math.pow(2, 10)];
        StringBuilder builder = new StringBuilder();
        try {
            while ((bytesRead = streamFromClient.read(request)) != -1) {
                streamToServer.write(request, 0, bytesRead);
                streamToServer.flush();
                builder.append(new String(request, 0, bytesRead));
            }
        } catch (IOException e) {

        }
        finally {
            log.debug("request from client:\n" + builder.toString());
        }


        try {
            streamToServer.close();
        } catch (IOException e) {
        }
    }
}
