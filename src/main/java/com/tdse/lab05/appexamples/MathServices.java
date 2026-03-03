package com.tdse.lab05.appexamples;

import com.tdse.lab05.HttpServer;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.tdse.lab05.HttpServer.get;
import static com.tdse.lab05.HttpServer.staticfiles;

public class MathServices {
    public static void main(String[] args) throws IOException, URISyntaxException {
        staticfiles("/webroot");
        get("/hello", (req, resp) -> "Hello " + req.getValues("name"));
        get("/pi", (req, resp) -> {
            return String.valueOf(Math.PI);
        });
        HttpServer.main(args);
    }

}
