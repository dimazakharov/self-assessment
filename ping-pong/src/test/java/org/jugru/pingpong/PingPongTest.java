package org.jugru.pingpong;

import org.jugru.pingpong.http.HTTPPingPing;
import org.jugru.pingpong.tcp.TCPPingPong;
import org.jugru.pingpong.udp.UDPPingPong;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PingPongTest {

    private final String pong = "pong";

    @Test
    public void HTTPTest() {
        String answer = new HTTPPingPing().start();
        Assertions.assertEquals(pong, answer);
    }

    @Test
    public void UDPTest() {
        String answer = new UDPPingPong().start();
        Assertions.assertEquals(pong, answer);
    }


    @Test
    public void TCPTest() {
        String answer = new TCPPingPong().start();
        Assertions.assertEquals(pong, answer);
    }
}

