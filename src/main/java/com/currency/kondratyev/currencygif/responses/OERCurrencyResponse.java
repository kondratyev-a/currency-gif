package com.currency.kondratyev.currencygif.responses;

import java.math.BigDecimal;
import java.util.Map;

public class OERCurrencyResponse {

    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    private Map<String, BigDecimal> rates;

    public OERCurrencyResponse() {
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    public BigDecimal getRateByCurrency(String name) {
        return rates.get(name);
    }
}
