package com.tdse.lab05;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private Map<String, String> queryParams = new HashMap<>();

    public void setQueryParams(String query) {
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] kv = pair.split("=", 2);
                if (kv.length == 2) {
                    queryParams.put(kv[0], kv[1]);
                } else {
                    queryParams.put(kv[0], "");
                }
            }
        }
    }

    public String getValue(String key) {
        return queryParams.getOrDefault(key, "");
    }

    // Alias requerido por el enunciado: req.getValues("name")
    public String getValues(String key) {
        return getValue(key);
    }
}
