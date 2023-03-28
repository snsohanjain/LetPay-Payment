package com.letpay.axies;

import org.springframework.stereotype.Component;

@Component
public class MongoConfig {
    private final String url = "mongodb+srv://sohanjain:sohanjain@cluster0.byn9s0t.mongodb.net/?retryWrites=true&w=majority";

    public String getUrl() {
        return url;
    }
}
