package com.letpay.axies;

import com.mongodb.client.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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
    private static final Logger LOGGER = Logger.getLogger(PaymentController.class);
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/payment-post")
    void createNewPayment(@RequestBody @Valid PPI ppi) throws NoSuchAlgorithmException {
        BasicConfigurator.configure();
        LOGGER.info(ppi);
        paymentService.getNewPaymentOrder(ppi);

    }

    @GetMapping("/payments/all")
    public List<Document> getAllPayments(){

        BasicConfigurator.configure();
        LOGGER.info("GET ALL PAYMENTS");

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
        LOGGER.info("RETURNED ALL PAYMENTS");
        return documents;
    }
}
