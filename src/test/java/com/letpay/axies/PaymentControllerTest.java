package com.letpay.axies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private ModelMap model;

    @Mock
    private PPI ppi;

    @Mock
    private BindingResult bindingResult;

    @Test
    public void testCreateNewPaymentOrder() throws NoSuchAlgorithmException {

        // Set up test data
        when(ppi.getAMT()).thenReturn((long) 6666);
        when(ppi.getCustomerName()).thenReturn("Sohan Jain");
        when(ppi.getPhoneNumber()).thenReturn("8880638514");
        when(ppi.getEmail()).thenReturn("snsohanjain@gmail.com");

        // Call the method under test
        String result = paymentController.createNewPaymentOrder(model, ppi, bindingResult);

        // Assert the result
        assertEquals("payment-success", result);
    }
}
