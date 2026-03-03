package com.tdse.lab05;


public class HttpResponse {
    private int statusCode = 200;
    private String contentType = "text/html";

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public String buildHeader() {
        return "HTTP/1.1 " + statusCode + " OK\r\n"
                + "Content-Type: " + contentType + "\r\n"
                + "\r\n";
    }
}
