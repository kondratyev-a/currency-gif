package com.currency.kondratyev.currencygif.services;

import com.currency.kondratyev.currencygif.externals.GiphyExternal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GifServiceGiphyTest {

    @Mock
    GiphyExternal giphyExternal;

    GifServiceGiphy gifService;

    public static final String URL = "https://test.com";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gifService = new GifServiceGiphy(giphyExternal);
        gifService.setIMAGE_PATH("/data/image_url");
    }

    @Test
    void getRandomGifByTag() {

        // given
        String response = "{\"data\":{\"image_url\":\"" + URL + "\"}}";

        // when
        when(giphyExternal.randomGif(any(), any())).thenReturn(response);
        String url = gifService.getRandomGifByTag("rich");

        // then
        assertEquals(URL, url);
        verify(giphyExternal, times(1)).randomGif(any(), any());
    }
}