package com.example.megacitycab.service;



import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

 public class PaymentServiceTest {


     @Test
     public void testProcessPayment_InvalidData() {
         PaymentService paymentService = new PaymentService();


         boolean result1 = paymentService.processPayment(-1, "CARD");
         assertFalse(result1, "Expected payment processing to fail for invalid booking ID (-1)");


         boolean result2 = paymentService.processPayment(5, "");
         assertFalse(result2, "Expected payment processing to fail for empty payment method");


         boolean result3 = paymentService.processPayment(0, "CASH");
         assertFalse(result3, "Expected payment processing to fail for booking ID 0");
     }

     @Test
     public void testProcessPayment_ValidData() {
         PaymentService paymentService = new PaymentService();


         boolean result = paymentService.processPayment(1, "CARD");


         assertTrue(result, "Expected payment processing to succeed for valid booking ID");
     }
}