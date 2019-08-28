package org.jugru.pingpong.http;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.util.stream.Collectors;

public class HTTPServer implements Closeable {


    Server server;

    public HTTPServer(int port) throws Exception {
        this.server = new Server(port);
        server.setHandler(new Handler());
        server.start();
    }


    @Override
    public void close() throws IOException {
        try {
            server.stop();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}


class Handler extends AbstractHandler {

    private final String ping = "ping";
    private final String pong = "pong";


    @Override
    public void handle(String target, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {

        if (requestValid(httpServletRequest)) {
            httpServletResponse.setContentType("text/plain;charset=utf-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            request.setHandled(true);
            httpServletResponse.getWriter().print(pong);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setHandled(true);
        }

    }

    private boolean requestValid(HttpServletRequest httpServletRequest) throws IOException {
        String body = httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String method = httpServletRequest.getMethod();

        if (!ping.equals(body))
            return false;
        return "POST".equals(method);
    }
}





