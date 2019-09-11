package org.jugru.proxy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProxyServer {

    final static ExecutorService executorService = Executors.newCachedThreadPool();

    public void start(String url, int resourcePort, int serverPort) {
        executorService.submit(new ProxyInstance(url, resourcePort, serverPort));
    }


}

