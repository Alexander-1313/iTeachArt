package com.iteachart.stock.controller;

import com.iteachart.stock.entity.Subscribe;
import com.iteachart.stock.service.PaypalService;
import com.iteachart.stock.util.StringConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaypalController {

    private final PaypalService service;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "home";
    }

    @GetMapping("/pay")
    public String payment(@ModelAttribute("subscribe") Subscribe subscribe) {
        try {
            Payment payment = service.createPayment(subscribe.getCost().doubleValue(), StringConstant.CURRENCY, StringConstant.METHOD,
                    StringConstant.INTENT, StringConstant.DESCRIPTION, StringConstant.CANCEL_URL_WITH_HOST,
                    StringConstant.SUCCESS_URL_WITH_HOST);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.trace(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping(value = StringConstant.CANCEL_URL)
    @ResponseBody
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = StringConstant.SUCCESS_URL)
    @ResponseBody
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            log.trace(e.getMessage());
        }
        return "redirect:/";
    }

}
