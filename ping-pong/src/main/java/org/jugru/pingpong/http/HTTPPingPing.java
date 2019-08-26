package org.jugru.pingpong.http;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPPingPing {
    private final String ping = "ping";
    private final int port = 5617;


    @SneakyThrows
    public String start() {
        try (HTTPServer server = new HTTPServer(5617)) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:" + port))
                    .POST(HttpRequest.BodyPublishers.ofString(ping))
                    .build();


            HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
    }
}
