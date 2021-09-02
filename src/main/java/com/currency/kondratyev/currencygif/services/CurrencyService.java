package com.currency.kondratyev.currencygif.services;


import java.math.BigDecimal;
import java.time.LocalDate;

public interface CurrencyService {

    BigDecimal getRateByCurrencyAndDate(String currency, LocalDate date);

}
