package com.razorpay.controller;

import com.razorpay.RazorpayException;
import com.razorpay.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/create-order")
    public String createOrder(@RequestParam("amount") int amount, @RequestParam("currency") String currency) throws RazorpayException {
        String orderId = paymentService.createOrder(amount, currency);
        return "Order created with ID:" + orderId;

    }
    //http://localhost:8080/payments/create-order?amount=55&currency=INR
    //http://localhost:8080/payments/verify-signature?orderId=567&paymentId=4567&signature=tabrez
    @PostMapping("/verify-signature")
    public String verifyPaymentSignature(@RequestParam("orderId")  String orderId,
                                         @RequestParam("paymentId") String paymentId,
                                         @RequestParam("signature") String signature) throws RazorpayException {
        return paymentService.verifyPaymentSignature(orderId, paymentId, signature);

    }
}
