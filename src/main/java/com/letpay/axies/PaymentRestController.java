package com.letpay.axies;

import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.letpay.axies.MongoConfig.mongoAccess;

@RestController
@RequestMapping(path = "/")
public class PaymentRestController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/payment-post")
    void createNewPayment(@RequestBody @Valid PPI ppi) throws NoSuchAlgorithmException {
        System.out.println(ppi);
        paymentService.getNewPaymentOrder(ppi);
    }

    @GetMapping("/payments/all")
    public List<Document> getAllPayments(){

        MongoClient mongoClient = MongoClients.create(mongoAccess);
        MongoDatabase database = mongoClient.getDatabase("payment");
        MongoCollection<Document> collection = database.getCollection("allpayments");

        List<Document> documents = new ArrayList<>();

        Document query = new Document(new Document());
        FindIterable<Document> iterable = collection.find(query);
        for (Document document : iterable) {
            Date date = document.getDate("localDate");
            Instant instant = date.toInstant();
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            String dateString = localDate.toString();
            document.put("localDate", dateString);
            documents.add(document);
        }
        mongoClient.close();
        return documents;
    }
}
