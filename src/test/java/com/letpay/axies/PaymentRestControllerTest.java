package com.letpay.axies;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PaymentRestControllerTest {
    @InjectMocks
    private PaymentRestController paymentRestController;
    @Test
    public void testGetAllPayments() {
        List<Document> payments = paymentRestController.getAllPayments();
        // Checking the returned documents
        assertEquals(payments, payments);
        System.out.println(payments);

    }
}
