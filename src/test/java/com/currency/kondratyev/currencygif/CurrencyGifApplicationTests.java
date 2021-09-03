package com.currency.kondratyev.currencygif;

import com.currency.kondratyev.currencygif.controllers.CurrencyGifController;
import com.currency.kondratyev.currencygif.services.CurrencyService;
import com.currency.kondratyev.currencygif.services.GifService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CurrencyGifApplicationTests {

    @Autowired
    CurrencyService currencyService;

    @Autowired
    GifService gifService;

    @Autowired
    CurrencyGifController currencyGifController;

    @Test
    void contextLoads() {
        assertNotNull(currencyService);
        assertNotNull(gifService);
        assertNotNull(currencyGifController);
    }

}
