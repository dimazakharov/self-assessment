package org.jugru.pingpong.tcp;

import lombok.SneakyThrows;

public class TCPPingPong {
    private final String ping = "ping";
    private final String pong = "pong";
    private final int port = 5656;

    @SneakyThrows
    public String start() {


        new Thread(new ServerThread()).start();

        try (TCPClient client = new TCPClient(port)) {

            client.send(ping);
            String in;
            do
                in = client.receive();
            while (!pong.equals(in));

            return in;
        }

    }

    class ServerThread implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            try (TCPServer tcpServer = new TCPServer(port);
                 TCPClient client = tcpServer.accept()) {

                String in;

                do
                    in = client.receive();
                while (!ping.equals(in));
                System.out.println("TCPServer input - " + in);
                client.send(pong);
            }

        }
    }

}

