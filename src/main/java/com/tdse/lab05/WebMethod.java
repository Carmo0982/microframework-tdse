package com.tdse.lab05;

@FunctionalInterface
public interface WebMethod {
    String execute(HttpRequest req, HttpResponse res);
}
