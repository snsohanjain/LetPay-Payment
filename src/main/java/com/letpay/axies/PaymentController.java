package com.letpay.axies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;


@Controller
@RequestMapping(path = "/")
public class PaymentController {
    private static final String  key = "axisbank12345678";
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public String welcomePage(){
        return "welcome";
    }
    @GetMapping("/payment")
    public String orderPage(){
        return "payment";
    }

    @PostMapping("/payment")
    String createNewPaymentOrder(ModelMap model, @Valid PaymentRequest paymentRequest,PPI ppi ,BindingResult result) throws NoSuchAlgorithmException {
        paymentService.getNewPaymentOrder(ppi);
        return "payment-success";
    }


}
