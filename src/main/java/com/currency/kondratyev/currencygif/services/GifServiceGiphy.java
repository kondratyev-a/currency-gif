package com.currency.kondratyev.currencygif.services;

import com.currency.kondratyev.currencygif.externals.GiphyExternal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GifServiceGiphy implements GifService {

    // TODO убрать в параметры
    private static final String API_KEY = "pIJan1zxTSXuNRz3xHfIlm3JvazT8WhI";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final GiphyExternal giphyExternal;

    public GifServiceGiphy(GiphyExternal giphyExternal) {
        this.giphyExternal = giphyExternal;
    }

    @Override
    public String getRandomGifByTag(String tag) {

        String response = giphyExternal.randomGif(API_KEY, tag);

        String gifUrl = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response);
            gifUrl = root.at("/data/image_url").asText();
        }
        catch(Exception e) {
            logger.error("Exception on parsing response {}", e.getMessage());
        }

        return gifUrl;
    }
}
