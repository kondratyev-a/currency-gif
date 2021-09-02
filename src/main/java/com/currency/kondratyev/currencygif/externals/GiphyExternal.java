package com.currency.kondratyev.currencygif.externals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gif-service", url = "${giphy.url}")
public interface GiphyExternal {

    @GetMapping("search")
    String searchGif(@RequestParam(name = "api_key") String apiKey,
                     @RequestParam(name = "q") String query);

    @GetMapping("random")
    String randomGif(@RequestParam(name = "api_key") String apiKey,
                     @RequestParam(name = "tag") String tag);

}
