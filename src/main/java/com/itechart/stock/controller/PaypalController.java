package com.itechart.stock.controller;

import com.itechart.stock.service.PaypalService;
import com.itechart.stock.util.StringConstant;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaypalController {

    private final PaypalService paypalService;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "home";
    }

    @GetMapping("/pay")
    @ResponseBody
    public String payment(@RequestParam("subscribe") String subscribe) throws PayPalRESTException {
        Payment payment = paypalService.createPayment(
                    subscribe,
                    StringConstant.CURRENCY,
                    StringConstant.METHOD,
                    StringConstant.INTENT,
                    StringConstant.DESCRIPTION,
                    StringConstant.CANCEL_URL_WITH_HOST,
                    StringConstant.SUCCESS_URL_WITH_HOST);
        log.info("payment={}", payment);
        return payment.toJSON();
    }

    @GetMapping(value = StringConstant.SUCCESS_URL)
    @ResponseBody
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            log.trace(e.getMessage());
        }
        return "cancle";
    }
}
