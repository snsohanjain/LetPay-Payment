package com.letpay.axies;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.letpay.axies.MongoConfig.mongoAccess;
import static com.mongodb.client.model.Filters.gte;

@RestController
@RequestMapping(path = "/")
public class PaymentRestController {

    @GetMapping("/payments/all")
    List<Object> getAllPayments(){

        MongoClient mongoClient = MongoClients.create(mongoAccess);
        MongoDatabase database = mongoClient.getDatabase("payment");
        MongoCollection<Document> collection = database.getCollection("allpayments");

        List<Document> documents = new ArrayList<>();

        Document query = new Document("AMT", new Document("$gt", 1));
        FindIterable<Document> iterable = collection.find(query);
        for (Document document : iterable) {

            documents.add(document);
        }
        mongoClient.close();
        return Collections.singletonList(documents);
    }
}
