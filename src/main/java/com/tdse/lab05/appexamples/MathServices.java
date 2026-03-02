package com.tdse.lab05.appexamples;

import com.tdse.lab05.HttpServer;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.tdse.lab05.HttpServer.get;
public class MathServices {
    public static void main(String[] args) throws IOException, URISyntaxException {
        get("/pi",(req,res) -> "PI= "+ Math.PI);
        get ("/Hello", (req,res)-> "Hello Word" );
        get("/frommethod", (req,res)-> euler());
        HttpServer.main(args);
    }

    private static String euler() {
        return "e= "+ Math.E;
    }

}
