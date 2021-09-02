package com.currency.kondratyev.currencygif.externals;

import com.currency.kondratyev.currencygif.responses.OERCurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "currency-service", url = "https://openexchangerates.org/api/")
public interface OERExternal {

    @GetMapping("historical/{date}.json")
        OERCurrencyResponse getRate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                @RequestParam(name = "app_id") String appId,
                                @RequestParam(name = "base") String base,
                                @RequestParam(name = "symbols") String symbols);

}
