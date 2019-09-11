package org.jugru.proxy;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ServerTest {

    String resourceUrl = "localhost";
    int resourcePort = 4143;
    String serverUrl = "localhost";
    int serverPort = 4172;

    private String requestAnswer = "Answer";

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup () {
        wireMockServer = new WireMockServer(resourcePort);
        wireMockServer.start();
        setupMockServer();
    }

    @AfterEach
    public void teardown () {
        wireMockServer.stop();
    }

    @Test
    public void ProxyServerTest() throws Exception {
        new ProxyServer().start(resourceUrl, resourcePort, serverPort);
        HttpResponse<String> response = makeRequestToProxy("");

        wireMockServer.verify(getRequestedFor(urlEqualTo("/")));
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(requestAnswer, response.body());
    }

    @Test
    public void ProxyServerDownloadTest() throws Exception {
        new ProxyServer().start(resourceUrl, resourcePort, serverPort);
        HttpResponse<String> response = makeRequestToProxy("download");

        wireMockServer.verify(getRequestedFor(urlEqualTo("/download")));
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.body().contains("Lorem ipsum dolor sit amet"));
        Assertions.assertTrue(response.body().contains("anim id est laborum."));
    }

    private HttpResponse<String> makeRequestToProxy(String param) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://" + serverUrl + ":" + serverPort + "/" + param))
                .GET()
                .build();


        return HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }

    private void setupMockServer() {
        wireMockServer.stubFor(get(urlEqualTo("/"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody(requestAnswer)));
        wireMockServer.stubFor(get(urlEqualTo("/download"))
                .willReturn(aResponse()
                        .withHeader("Content-Disposition", "attachment; filename=LoremIpsum.txt")
                        .withBodyFile("LoremIpsum.txt")));
    }
}
