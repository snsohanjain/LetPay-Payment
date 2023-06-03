package com.letpay.axies;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;

import static com.letpay.axies.MongoConfig.mongoAccess;
import static com.letpay.axies.RandomAlphaNumeric.generateRandomNumeric;
import static com.letpay.axies.RandomAlphaNumeric.generateRandomString;

@Service
public class PaymentService {
    private static final String  key = "axisbank12345678";
    private static final Logger LOGGER = Logger.getLogger(PaymentController.class);

    @PostMapping
    String getNewPaymentOrder(PPI ppi) throws NoSuchAlgorithmException {

        BasicConfigurator.configure();
        LOGGER.info("CREATE A PAYMENT REQUEST");

        //RANDOM-VALUE
        String randomBNR = generateRandomString(6);
        String randomTRN = generateRandomString(25);
        String randomRID = generateRandomString(12);
        Long randomCRN = generateRandomNumeric(12);
        Long referenceId = generateRandomNumeric(12);
//------------------------------------------------------------------------------------------------------
        //Payment Request
        PaymentRequest paymentRequestPass = new PaymentRequest(
                6994, randomRID, randomCRN,ppi.getAMT()
                ,1.0,"TEST","INR", "https://www.letpay.in/pay/easypay/payment/thankyou",
                new PPI(referenceId,ppi.getCustomerName(), LocalDateTime.now(),ppi.getPhoneNumber(),
                        ppi.getEmail(),ppi.getAMT()),"12345");

//-------------------------------------------------------------------------------------------
        //ENCRYPTED
        System.out.println("REQUEST : " + paymentRequestPass);
        System.out.println();
        String encryptedResponse = EncryptionAndDecryptionMain.encrypt(paymentRequestPass.toString(),key);
        //Encrypted Response
        System.out.println("ENCRYPTED REQUEST: " + "i="+encryptedResponse+"&"+"token");
        System.out.println();
        //Decrypted Response
        String decryptedResponse = EncryptionAndDecryptionMain.decrypt(encryptedResponse,key);
        System.out.println("DECRYPTED REQUEST: " + decryptedResponse);
        System.out.println();
        //Checksum Response
        String checksum = EncryptionAndDecryptionMain.checkSum(encryptedResponse);
        System.out.println("Checksum Response: " + checksum);
//-----------------------------------------------------------------------------------------------
        //PAYMENT RESPONSE
        PaymentResponse paymentResponse = new PaymentResponse(
                randomBNR,200,"Success",randomTRN, LocalDateTime.now(),
                "UPI",randomRID,1.0, 6994,"UPI",randomCRN,
                "INR",paymentRequestPass.getPPI().getAMT());
//--------------------------------------------------------------------------------------------------
        //MONGODB
        MongoClient mongoClient = MongoClients.create(mongoAccess);
        MongoDatabase db = mongoClient.getDatabase("payment");
        MongoCollection col = db.getCollection("allpayments");


        Map<String, Object> paymentmap = MongoConfig.toMap(paymentRequestPass.getPPI(),paymentResponse);
        Document document = new Document(paymentmap);
        col.insertOne(document);
        LOGGER.info("PAYMENT REQUEST SUCCESS STORED IN MONGODB");

        BasicConfigurator.configure();
        LOGGER.info("Payment Success");

        return null;

    }
}
