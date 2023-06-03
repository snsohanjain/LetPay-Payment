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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.letpay.axies.MongoConfig.mongoAccess;

@CrossOrigin
@RestController
@RequestMapping(path = "/")
public class PaymentRestController {
    private static final Logger LOGGER = Logger.getLogger(PaymentController.class);
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/payment-post")
    void createNewPayment(@RequestBody @Valid PPI ppi) throws NoSuchAlgorithmException {
        System.out.println(ppi);
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
//--------------------------------------------------------------------------------------------------
        List<Document> documents = new ArrayList<>();
        Document query = new Document(new Document());
        FindIterable<Document> iterable = collection.find(query);
        for (Document document : iterable) {

            Date date = document.getDate("localDateTime");
            Instant instant = date.toInstant();
            LocalDateTime localDatetime =  LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            String dateString = localDatetime.toString();
            document.put("localDateTime", dateString);
            //------------------------------------------------------------------------------------
            Document paymentResponseDoc = document.get("PaymentResponse", Document.class);
            Date date2 = paymentResponseDoc.getDate("localDateTime");
            Instant instant2 = date2.toInstant();
            LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());
            String dateString2 = localDateTime2.toString();
            paymentResponseDoc.put("localDateTime", dateString2);
            document.put("PaymentResponse", paymentResponseDoc);
            documents.add(document);
        }
        mongoClient.close();
        LOGGER.info("RETURNED ALL PAYMENTS");
        return documents;
    }

    @GetMapping("/payments")
    public List<Document> getAllPayments(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "100") int size) {

        BasicConfigurator.configure();
        LOGGER.info("GET ALL PAYMENTS");

        MongoClient mongoClient = MongoClients.create(mongoAccess);
        MongoDatabase database = mongoClient.getDatabase("payment");
        MongoCollection<Document> collection = database.getCollection("allpayments");
        List<Document> documents = new ArrayList<>();

        // calculate the number of documents to skip based on the page and size parameters
        int skip = page * size;

        // limit the number of returned documents to the specified size (maximum 1000)
        int limit = Math.min(size, 1000);

        // find the documents in the collection using skip and limit
        FindIterable<Document> iterable = collection.find().skip(skip).limit(limit);
        for (Document document : iterable) {
            Date date = document.getDate("localDateTime");
            Instant instant = date.toInstant();
            LocalDateTime localDatetime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            String dateString = localDatetime.toString();
            document.put("localDateTime", dateString);
            Document paymentResponseDoc = document.get("PaymentResponse", Document.class);
            Date date2 = paymentResponseDoc.getDate("localDateTime");
            Instant instant2 = date2.toInstant();
            LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());
            String dateString2 = localDateTime2.toString();
            paymentResponseDoc.put("localDateTime", dateString2);
            document.put("PaymentResponse", paymentResponseDoc);
            documents.add(document);
        }

        mongoClient.close();
        LOGGER.info("RETURNED ALL PAYMENTS");

        return documents;
    }

}
