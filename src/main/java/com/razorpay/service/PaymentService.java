package com.razorpay.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.razorpay.config.RazorpayConfig;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final RazorpayConfig razorpayConfig;

    public PaymentService(RazorpayConfig razorpayConfig) {
        this.razorpayConfig = razorpayConfig;
    }
    public String createOrder( int amount, String currency) throws RazorpayException{
        RazorpayClient client = new RazorpayClient(razorpayConfig.getRazorpayKeyId(),
                razorpayConfig.getRazorpayKeySecret());

        JSONObject options = new JSONObject();
        options.put("amount", amount);
        options.put("currency", currency);
        Order order = client.orders.create(options);
        return order.get("id");



    }
    public String verifyPaymentSignature(String orderId, String paymentId, String signature) throws RazorpayException {
        RazorpayClient client = new RazorpayClient(razorpayConfig.getRazorpayKeyId(),
                razorpayConfig.getRazorpayKeySecret());
        JSONObject attributes = new JSONObject(client);
        attributes.put("razorpay_order_id", orderId);
        attributes.put("razorpay_payment_id", paymentId);
        attributes.put("razorpay_signature", signature);
        Utils.verifyPaymentSignature(attributes,signature);

        return "Payment signature verified successfully";

    }
}
