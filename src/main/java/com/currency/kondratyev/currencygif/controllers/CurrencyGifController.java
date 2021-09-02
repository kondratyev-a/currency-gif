package com.currency.kondratyev.currencygif.controllers;

import com.currency.kondratyev.currencygif.responses.CurrencyResponse;
import com.currency.kondratyev.currencygif.services.CurrencyService;
import com.currency.kondratyev.currencygif.services.GifService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class CurrencyGifController {

    private static final String CURRENCY = "RUB";
    private static final String RICH = "rich";
    private static final String BROKE = "broke";
    private static final String FILE_URL = "https://media2.giphy.com/media/Q7SJW7IBeuuRFwjj5G/giphy.gif";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CurrencyService currencyService;
    private final GifService gifService;

    public CurrencyGifController(CurrencyService currencyService, GifService gifService) {
        this.currencyService = currencyService;
        this.gifService = gifService;
    }

    @GetMapping(value = "/rest")
    @ResponseBody
    public ResponseEntity<CurrencyResponse> showJson() {
        return ResponseEntity.ok(new CurrencyResponse(getGifUrl()));
    }

    @GetMapping("/redirect")
    public ModelAndView showRedirect() {
        return new ModelAndView("redirect:" + getGifUrl());
    }

    @GetMapping("/index")
    public String showWebPage(Model model) {
        model.addAttribute("url", getGifUrl());
        return "index";
    }

    private String getGifUrl() {
        LocalDate today = LocalDate.now();
        BigDecimal todayRate = currencyService.getRateByCurrencyAndDate(CURRENCY, today);

        LocalDate yesterday = today.minusDays(1);
        BigDecimal yesterdayRate = currencyService.getRateByCurrencyAndDate(CURRENCY, yesterday);

        // TODO Проверить условие
        boolean rateIncreased = (todayRate.compareTo(yesterdayRate) < 0);
        logger.info((rateIncreased ? "Rate increased" : "Rate decreased"));

        String tag = (rateIncreased ? RICH : BROKE);

        return gifService.getRandomGifByTag(tag);
    }

}