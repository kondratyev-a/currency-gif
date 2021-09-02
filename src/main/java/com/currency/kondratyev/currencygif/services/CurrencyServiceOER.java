package com.currency.kondratyev.currencygif.services;

import com.currency.kondratyev.currencygif.externals.OERExternal;
import com.currency.kondratyev.currencygif.responses.OERCurrencyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CurrencyServiceOER implements CurrencyService {

    @Value("${openexchangerates.app_id}")
    private String APP_ID;

    @Value("${main.base}")
    private String BASE;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OERExternal oerExternal;

    public CurrencyServiceOER(OERExternal oerExternal) {
        this.oerExternal = oerExternal;
    }

    @Override
    public BigDecimal getRateByCurrencyAndDate(String currency, LocalDate date) {

        OERCurrencyResponse response = oerExternal.getRate(date, APP_ID, BASE, currency);
        BigDecimal rate = response.getRateByCurrency(currency);
        logger.info("Exchange rate of {} in relation to {} on date {} is {}", currency, BASE, date, rate);

        return rate;
    }
}
