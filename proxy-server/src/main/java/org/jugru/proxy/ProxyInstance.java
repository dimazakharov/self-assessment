package org.jugru.proxy;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;


class ProxyInstance implements Callable<Void> {

    private final String url;
    private final int resourcePort;
    private final int serverPort;


    public ProxyInstance(String url, int resourcePort, int serverPort) {
        this.url = url;
        this.resourcePort = resourcePort;
        this.serverPort = serverPort;
    }

    @Override
    public Void call() throws Exception {
        ServerSocket ss = new ServerSocket(serverPort);
        while (true) {
            Socket client = ss.accept();
            ProxyServer.executorService.submit(new SingleRequestHandler(client, url, resourcePort));
        }
    }


}
