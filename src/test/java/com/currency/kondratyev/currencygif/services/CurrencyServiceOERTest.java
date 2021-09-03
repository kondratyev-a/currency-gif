package com.currency.kondratyev.currencygif.services;

import com.currency.kondratyev.currencygif.externals.OERExternal;
import com.currency.kondratyev.currencygif.responses.OERCurrencyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CurrencyServiceOERTest {

    @Mock
    OERExternal oerExternal;

    CurrencyServiceOER currencyService;

    public static final String CURRENCY = "RUB";
    public static final BigDecimal RATE = BigDecimal.valueOf(65.5125);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        currencyService = new CurrencyServiceOER(oerExternal);
    }

    @Test
    void getRateByCurrencyAndDate() {

        // given
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put(CURRENCY, RATE);

        OERCurrencyResponse response = new OERCurrencyResponse();
        response.setRates(rates);

        // when
        when(oerExternal.getRate(any(), any(), any(), anyString())).thenReturn(response);
        BigDecimal rate = currencyService.getRateByCurrencyAndDate(CURRENCY, LocalDate.now());

        // then
        assertEquals(RATE, rate);
        verify(oerExternal, times(1)).getRate(any(), any(), any(), anyString());
    }
}