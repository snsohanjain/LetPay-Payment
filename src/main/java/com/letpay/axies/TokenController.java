package com.letpay.axies;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;
@Controller
@RequestMapping(path = "/")
public class TokenController {

    private static final Logger LOGGER = Logger.getLogger(PaymentController.class);
    private static final String  key = "axisbank12345678";
    @GetMapping( "/token")
    public String getTokens(ModelMap model) throws IOException {
        String encryptedCID = EncryptionAndDecryptionMain.encrypt("6994",key);
        System.out.println("Encrypt CID  :: " + encryptedCID);
        String decryptedCID  = EncryptionAndDecryptionMain.decrypt(encryptedCID,key);
        System.out.println("Decrypt CID  :: " + decryptedCID);

        BasicConfigurator.configure();
        LOGGER.info("LOGGER :: = Creating a HTTP Client");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        okhttp3.RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("CID", encryptedCID).build();

        LOGGER.info("LOGGER :: = Requesting the HTTP POST TO GET THE TOKEN");
        Request request = new Request.Builder()
                .url("https://uat-etendering.axisbank.co.in/easypay2.0/frontend/api/generatetokencreate")
                .method("POST",body)
                .build();

        LOGGER.info("LOGGER :: = Response from Bank Api IT SHOULD BE THE TOKEN");
        Response response = client.newCall(request).execute();
        LOGGER.info("LOGGER :: = TOKEN RESPONSE?");
        System.out.println(response.body().string());
        model.addAttribute("token",response.body().toString());
        LOGGER.info("LOGGER :: = TOKEN RESPONSE!!!!!!!!!!!!");
        return "token";
    }
}
