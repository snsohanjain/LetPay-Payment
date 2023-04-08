package com.letpay.axies;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;
@Controller
@RequestMapping(path = "/")
public class TokenController {

    private Log logger = LogFactory.getLog(TokenController.class);
    private static final String  key = "axisbank12345678";
    @GetMapping( "/token")
    public String getTokens(ModelMap model) throws IOException {
        String encryptedCID = EncryptionAndDecryptionMain.encrypt("6994",key);
        System.out.println("Encrypt CID  :: " + encryptedCID);
        String decryptedCID  = EncryptionAndDecryptionMain.decrypt(encryptedCID,key);
        System.out.println("Decrypt CID  :: " + decryptedCID);

        logger.info("LOGGER :: = Creating a HTTP Client");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        okhttp3.RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("CID", encryptedCID).build();

        logger.info("LOGGER :: = Requesting the HTTP POST TO GET THE TOKEN");
        Request request = new Request.Builder()
                .url("https://uat-etendering.axisbank.co.in/easypay2.0/frontend/api/generatetokencreate")
                .method("POST",body)
                .build();

        logger.info("LOGGER :: = Response from Bank Api IT SHOULD BE THE TOKEN");
        Response response = client.newCall(request).execute();
        logger.info("LOGGER :: = TOKEN RESPONSE?");
        System.out.println(response.body().string());
        model.addAttribute("token",response.body().toString());
        return "token";
    }
}
