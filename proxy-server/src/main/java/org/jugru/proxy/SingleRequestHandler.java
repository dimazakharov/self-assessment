package org.jugru.proxy;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
class SingleRequestHandler implements Runnable {
    private final Socket client;
    private final String url;
    private final int resourcePort;

    public SingleRequestHandler(Socket client, String url, int resourcePort) {
        this.client = client;
        this.url = url;
        this.resourcePort = resourcePort;
    }

    @Override
    public void run() {
        try (Socket server = new Socket(url, resourcePort);
             InputStream streamFromClient = client.getInputStream();
             OutputStream streamToClient = client.getOutputStream();
             InputStream streamFromServer = server.getInputStream();
             OutputStream streamToServer = server.getOutputStream()) {

            ProxyServer.executorService.submit(new ClientToServer(streamFromClient, streamToServer));

            int bytesRead;
            byte[] reply = new byte[(int) Math.pow(2, 12)];

            StringBuilder builder = new StringBuilder();
            try {
                while ((bytesRead = streamFromServer.read(reply)) != -1) {
                    streamToClient.write(reply, 0, bytesRead);
                    streamToClient.flush();
                    builder.append(new String(reply, 0, bytesRead));
                }
            } catch (IOException e) {
            } finally {
                log.debug("response from server:\n" + builder.toString());
            }


        } catch (IOException e) {
        }
    }
}
