package com.tdse.lab05;

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class HttpServer {
    static Map<String, WebMethod> endPoints = new HashMap<>();
    static String staticFilesLocation = "";
    public static void main(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;


        boolean running = true;


        while (running) {
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }


            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine = "";


            boolean firstLine = true;

            String reqpath = "";
            String queryString = "";

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstLine) {
                    String[] reqTokens = inputLine.split(" ");
                    String method = reqTokens[0];
                    String struri = reqTokens[1];
                    String protocol = reqTokens[2];

                    URI requri = new URI(struri);

                    reqpath = requri.getPath();
                    queryString = requri.getQuery() != null ? requri.getQuery() : "";

                    System.out.println("Request path: " + reqpath);

                    firstLine = false;
                }

                if (!in.ready()) {
                    break;
                }
            }

            HttpRequest req = new HttpRequest();
            req.setQueryParams(queryString);
            HttpResponse res = new HttpResponse();

            if (endPoints.containsKey(reqpath)) {
                WebMethod wm = endPoints.get(reqpath);
                String body = wm.execute(req, res);
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>" + reqpath + "</title>"
                        + "</head>"
                        + "<body>"
                        + body
                        + "</body>"
                        + "</html>";
            } else {
                String filePath = "target/classes/"
                        + (staticFilesLocation.isEmpty() ? "" : staticFilesLocation + "/")
                        + reqpath;
                File staticFile = new File(filePath);

                if (!staticFilesLocation.isEmpty() && staticFile.exists() && staticFile.isFile()) {
                    String contentType = getContentType(reqpath);
                    byte[] fileBytes = Files.readAllBytes(staticFile.toPath());
                    String header = "HTTP/1.1 200 OK\r\n"
                            + "Content-Type: " + contentType + "\r\n"
                            + "Content-Length: " + fileBytes.length + "\r\n"
                            + "\r\n";
                    OutputStream rawOut = clientSocket.getOutputStream();
                    rawOut.write(header.getBytes());
                    rawOut.write(fileBytes);
                    rawOut.flush();
                    out.close();
                    in.close();
                    clientSocket.close();
                    continue;
                } else {
                    outputLine
                            = "HTTP/1.1 404 Not Found\r\n"
                            + "Content-Type: text/html\r\n"
                            + "\r\n"
                            + "<!DOCTYPE html>"
                            + "<html>"
                            + "<head>"
                            + "<meta charset=\"UTF-8\">"
                            + "<title>404 Not Found</title>"
                            + "</head>"
                            + "<body>"
                            + "<h1>404 - Resource not found</h1>"
                            + "</body>"
                            + "</html>";
                }
            }
            out.println(outputLine);


            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }
    public static void get(String path, WebMethod wm) {
        endPoints.put(path, wm);
    }

    public static void staticfiles(String folder) {
        staticFilesLocation = folder;
    }

    private static String getContentType(String path) {
        if (path.endsWith(".html") || path.endsWith(".htm")) return "text/html";
        if (path.endsWith(".css"))   return "text/css";
        if (path.endsWith(".js"))    return "application/javascript";
        if (path.endsWith(".png"))   return "image/png";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
        if (path.endsWith(".gif"))   return "image/gif";
        if (path.endsWith(".ico"))   return "image/x-icon";
        if (path.endsWith(".svg"))   return "image/svg+xml";
        if (path.endsWith(".json"))  return "application/json";
        return "application/octet-stream";
    }
}
