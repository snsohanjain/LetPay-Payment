package com.letpay.axies;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class MongoConfig {
    public final static String mongoAccess = "mongodb+srv://sohanjain:sohanjain@cluster0.byn9s0t.mongodb.net/?retryWrites=true&w=majority";
    public static Map toMap(PPI ppi, PaymentResponse paymentResponse) {

        SecureRandom random = new SecureRandom();
        int randomNumber =  random.nextInt(1000000000); // Generates a random 10-digit number

        Map<String, Object> map = new HashMap<>();
        map.put("_id", randomNumber);
        map.put("localDateTime",ppi.getLocalDateTime());
        map.put("referenceId", ppi.getReferenceId());
        map.put("customerName", ppi.getCustomerName());
        map.put("phoneNumber", ppi.getPhoneNumber());
        map.put("email", ppi.getEmail());
        map.put("amount", ppi.getAMT());
        map.put("PaymentResponse",paymentResponseMap(paymentResponse));
        return map;
    }
    public static Map paymentResponseMap(PaymentResponse paymentResponse){
        Map<String, Object> map = new HashMap<>();
        map.put("bankReferenceNumber", paymentResponse.getBRN());
        map.put("statusCode", paymentResponse.getSTC());
        map.put("paymentStatus", paymentResponse.getRMK());
        map.put("transactionID ", paymentResponse.getTRN());
        map.put("localDateTime", paymentResponse.getTET());
        map.put("paymentMode", paymentResponse.getPMD());
        map.put("referenceId", paymentResponse.getRID());
        map.put("paymentGateway", paymentResponse.getTYP());
        map.put("customerReferenceNumber", paymentResponse.getCRN());
        map.put("amount", paymentResponse.getAMT());
        return map;
    }


}
