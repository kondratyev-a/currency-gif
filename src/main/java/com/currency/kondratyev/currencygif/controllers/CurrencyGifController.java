package com.currency.kondratyev.currencygif.controllers;

import com.currency.kondratyev.currencygif.responses.CurrencyResponse;
import com.currency.kondratyev.currencygif.services.CurrencyGifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CurrencyGifController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CurrencyGifService service;

    public CurrencyGifController(CurrencyGifService service) {
        this.service = service;
    }

    @GetMapping({"/", "/index"})
    public String showWebPage(@RequestParam(defaultValue = "${main.currency}") String currency, Model model) {

        logger.info("Called html version with currency {}", currency);
        model.addAttribute("url", service.getGifUrlByCurrency(currency));
        return "index";
    }

    @GetMapping(value = "/rest")
    @ResponseBody
    public ResponseEntity<CurrencyResponse> showJson(@RequestParam(defaultValue = "${main.currency}") String currency) {

        logger.info("Called json version with currency {}", currency);
        return ResponseEntity.ok(new CurrencyResponse(service.getGifUrlByCurrency(currency)));
    }

    @GetMapping("/redirect")
    public ModelAndView showRedirect(@RequestParam(defaultValue = "${main.currency}") String currency) {

        logger.info("Called redirect version with currency {}", currency);
        return new ModelAndView("redirect:" + service.getGifUrlByCurrency(currency));
    }

}