package dev.mateus;


import dev.mateus.config.Configuration;
import dev.mateus.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.CRL;

public class HttpServer {
    // webroot é a raiz do site, onde o '/' vai,
    public static void main (String... args) {
        ConfigurationManager.loadConfig();
        Configuration conf = ConfigurationManager.configuration;

        System.out.println("Starting server on port: " + conf.getPort());
        System.out.println("WebRoot: " + conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String HTML = "<html><head><title>HTTP server</title></head><body><h1>HTTP hardcoded server</h1></body></html>";


            final String CRLF = "\n\r"; // 13, 10

            String response =
                    "HTTP/1.1 200 OK" + CRLF +  //status line : HTTP VERSION RESPONSE CODE - RESPONSE MESSAGE
                    "Content-Length: " + HTML.getBytes().length + CRLF + //header
                            CRLF +
                            HTML +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            serverSocket.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
