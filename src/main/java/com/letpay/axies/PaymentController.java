package com.letpay.axies;


import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.letpay.axies.MongoConfig.mongoAccess;
import static com.mongodb.client.model.Filters.gte;


@Controller
@RequestMapping(path = "/")
public class PaymentController {
    private static final String  key = "axisbank12345678";

    @GetMapping
    public String welcomePage(){
        return "welcome";
    }
    @GetMapping("/payment")
    public String orderPage(){
        return "payment";
    }
    @GetMapping("/payments/1")
    String getAllPayments(ModelMap model){

        MongoClient mongoClient = MongoClients.create(mongoAccess);
        MongoDatabase database = mongoClient.getDatabase("payment");
        MongoCollection<Document> collection = database.getCollection("paysuccess");
        Document doc = collection.find(Filters.and(gte("AMT", 99), Filters.eq("customerName", "sohan"))).first();
        System.out.println(doc.toJson());
        Document doc1 = collection.find(Filters.eq("phoneNumber", "8880638514")).first();
        System.out.println(doc1);
        //
        List<Document> documents = new ArrayList<>();
        Document query = new Document("AMT", new Document("$gt", 1));
        FindIterable<Document> iterable = collection.find(query);
        for (Document document : iterable) {
//            System.out.println(document);
            documents.add(document);
        }
        System.out.println(documents);
        model.addAttribute("documents", documents);
        mongoClient.close();

        return "all-payments";
    }

    @PostMapping("/payment")
    String createNewPaymentOrder(ModelMap model, @Valid PaymentRequest paymentRequest,PPI ppi ,BindingResult result){

       //BUSINESS LOGIC
        PaymentRequest paymentRequestPass = new PaymentRequest(
                6994, UUID.randomUUID().toString(), UUID.randomUUID(),ppi.getAMT()
                ,1.0,"TEST","INR", "https://www.letpay.in/pay/easypay/payment/thankyou",
                new PPI(UUID.randomUUID().toString(),ppi.getCustomerName(), LocalDate.now(),ppi.getPhoneNumber(),
                        ppi.getEmail(),ppi.getAMT()),"12345");

        //RESPONSE
        System.out.println("USER REQUEST : " + paymentRequestPass);
        String encryptedResponse = EncryptionAndDecryptionMain.encrypt(paymentRequestPass.toString(),key);
        //Encrypted Response
        System.out.println("ENCRYPTED REQUEST: " + "i="+encryptedResponse+"&");
        //Decrypted Response
        String decryptedResponse = EncryptionAndDecryptionMain.decrypt(encryptedResponse,key);
        System.out.println("DECRYPTED REQUEST: " + decryptedResponse);

        //MONGODB
        MongoClient mongoClient = MongoClients.create(mongoAccess);
        MongoDatabase db = mongoClient.getDatabase("payment");
        MongoCollection col = db.getCollection("allpayments");
        Map<String, Object> paymentmap = PPI.toMap(paymentRequestPass.getPPI());
        Document document = new Document(paymentmap);
        col.insertOne(document);

        return "payment-success";
    }


}
