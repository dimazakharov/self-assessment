package org.jugru.pingpong.udp;

import lombok.SneakyThrows;

public class UDPPingPong  {
    private final String ping = "ping";
    private final String pong = "pong";
    private final int serverPort = 5657;
    private final int clientPort = 5658;

    @SneakyThrows
    public String start() {


        new Thread(new ServerThread()).start();

        try (UDPClient client = new UDPClient(serverPort);
             UDPServer udpServer = new UDPServer(clientPort)) {

            client.send(ping);
            String in;
            do
                in = udpServer.receive();
            while (!pong.equals(in));
            System.out.println("UDPClient input - " + in);

            return in;
        }

    }

    class ServerThread implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            try (UDPServer udpServer = new UDPServer(serverPort);
                 UDPClient client = new UDPClient(clientPort)) {

                String in;

                do
                    in = udpServer.receive();
                while (!ping.equals(in));
                System.out.println("UDPServer input - " + in);
                client.send(pong);
            }

        }
    }
}
