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
     @Test
     public void testGetTotalEarnings() {

         PaymentService paymentService = new PaymentService();
         double totalEarnings = paymentService.getTotalEarnings();
         assertTrue(totalEarnings >= 0, "Total earnings should be a non-negative value");
     }

     @Test
     public void testGetEarningsByDateRange() {
         PaymentService paymentService = new PaymentService();

         String startDate = "2025-02-09";
         String endDate = "2025-03-10";


         double earnings = paymentService.getEarningsByDateRange(startDate, endDate);

         assertTrue(earnings >= 0, "Earnings within the date range should be a non-negative value");
     }
}