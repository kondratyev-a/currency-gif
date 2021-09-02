package com.currency.kondratyev.currencygif.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CurrencyGifService {

    @Value("${main.rich}")
    private String RICH;

    @Value("${main.broke}")
    private String BROKE;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CurrencyService currencyService;
    private final GifService gifService;

    public CurrencyGifService(CurrencyService currencyService, GifService gifService) {
        this.currencyService = currencyService;
        this.gifService = gifService;
    }

    public String getGifUrlByCurrency(String currency) {

        LocalDate today = LocalDate.now();
        BigDecimal todayRate = currencyService.getRateByCurrencyAndDate(currency, today);

        LocalDate yesterday = today.minusDays(1);
        BigDecimal yesterdayRate = currencyService.getRateByCurrencyAndDate(currency, yesterday);

        boolean rateIncreased = (todayRate.compareTo(yesterdayRate) > 0);
        logger.info((rateIncreased ? "Rate increased" : "Rate decreased"));

        String tag = (rateIncreased ? RICH : BROKE);

        return gifService.getRandomGifByTag(tag);
    }

}
