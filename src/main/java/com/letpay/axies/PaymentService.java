package com.letpay.axies;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static com.letpay.axies.MongoConfig.mongoAccess;
@Service
public class PaymentService {
    private static final String  key = "axisbank12345678";
    private static final Logger LOGGER = Logger.getLogger(PaymentController.class);
    @Autowired
    private PaymentController paymentController;

    @PostMapping
    String getNewPaymentOrder(PPI ppi) throws NoSuchAlgorithmException {
        BasicConfigurator.configure();
        LOGGER.info("CREATE A PAYMENT REQUEST");

        //RANDOM-VALUE
        SecureRandom random = new SecureRandom();
        long randomNumber = random.nextLong() % 1000000000000000L; // Generates a random 15-digit number
        if (randomNumber < 0) {
            randomNumber += 1000000000000000L;
        }
        String randomString = Long.toString(randomNumber);


        //BUSINESS LOGIC
        PaymentRequest paymentRequestPass = new PaymentRequest(
                6994, UUID.randomUUID().toString(), UUID.randomUUID(),ppi.getAMT()
                ,1.0,"TEST","INR", "https://www.letpay.in/pay/easypay/payment/thankyou",
                new PPI(randomString,ppi.getCustomerName(), LocalDate.now(),ppi.getPhoneNumber(),
                        ppi.getEmail(),ppi.getAMT()),"12345");

        //RESPONSE
        System.out.println("USER REQUEST : " + paymentRequestPass);
        String encryptedResponse = EncryptionAndDecryptionMain.encrypt(paymentRequestPass.toString(),key);
        //Encrypted Response
        System.out.println("ENCRYPTED REQUEST: " + "i="+encryptedResponse+"&"+"token");
        //Decrypted Response
        String decryptedResponse = EncryptionAndDecryptionMain.decrypt(encryptedResponse,key);
        System.out.println("DECRYPTED REQUEST: " + decryptedResponse);
        //Checksum Response
        String checksum = EncryptionAndDecryptionMain.checkSum(encryptedResponse);
        System.out.println("Checksum Response: " + checksum);

        //MONGODB
        MongoClient mongoClient = MongoClients.create(mongoAccess);
        MongoDatabase db = mongoClient.getDatabase("payment");
        MongoCollection col = db.getCollection("allpayments");
        Map<String, Object> paymentmap = PPI.toMap(paymentRequestPass.getPPI());
        Document document = new Document(paymentmap);
        col.insertOne(document);
        LOGGER.info("PAYMENT REQUEST SUCCESS STORED IN MONGODB");

        return null;

    }
}
