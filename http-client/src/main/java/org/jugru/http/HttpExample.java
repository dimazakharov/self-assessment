package org.jugru.http;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class HttpExample {

    @SneakyThrows
    public void execute(){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://www.google.com/"))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println("Body - " + response.body());

        System.out.println("status - " + response.headers().allValues(":status"));
        System.out.println("cache-control - " + response.headers().allValues("cache-control"));
        System.out.println("content-type - " + response.headers().allValues("content-type"));
        System.out.println("date - " + response.headers().allValues("date"));
        System.out.println("cookie - " + response.headers().allValues("set-cookie"));


    }


    public static void main(String[] args) {
        new HttpExample().execute();
    }
}
