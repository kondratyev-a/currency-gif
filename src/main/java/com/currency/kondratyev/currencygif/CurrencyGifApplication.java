package com.currency.kondratyev.currencygif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.currency.kondratyev.currencygif.externals")
@EnableDiscoveryClient
public class CurrencyGifApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyGifApplication.class, args);
    }

}
