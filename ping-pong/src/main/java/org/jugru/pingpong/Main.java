package org.jugru.pingpong;

import org.jugru.pingpong.udp.UDPPingPong;

public class Main {
    public static void main(String[] args) {
//        new TCPPingPong().start();
        new UDPPingPong().start();

    }
}
