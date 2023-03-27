package com.letpay.axies;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static com.mongodb.client.model.Filters.gte;


@Controller
@RequestMapping(path = "/")
public class PaymentController {

    private final String mongoAccess = "mongodb+srv://sohanjain:sohanjain@cluster0.byn9s0t.mongodb.net/?retryWrites=true&w=majority";

   @GetMapping
    public String welcomePage(){
        return "welcome";
    }
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String getPayment(){
        return "payments";
    }
    @GetMapping("/payment")
    public String orderPage(){
        return "payment";
    }
    @GetMapping("/payments/all")
    String getAllPayments(){
        MongoClient mongoClient = MongoClients.create(mongoAccess);
        MongoDatabase database = mongoClient.getDatabase("payment");
        MongoCollection<Document> collection = database.getCollection("paysuccess");
        Document doc = collection.find(Filters.and(gte("AMT", 99), Filters.eq("customerName", "sohan"))).first();
        System.out.println(doc.toJson());
        return doc.toJson();
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
        System.out.println(paymentRequestPass);

        //MONGODB
        MongoClient mongoClient = MongoClients.create(mongoAccess);
        MongoDatabase db = mongoClient.getDatabase("payment");
        MongoCollection col = db.getCollection("paysuccess");
        Map<String, Object> paymentmap = PPI.toMap(paymentRequestPass.getPPI());
        Document document = new Document(paymentmap);
        col.insertOne(document);

        return "payment-success";
    }


}
