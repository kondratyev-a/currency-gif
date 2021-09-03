package com.currency.kondratyev.currencygif.controllers;

import com.currency.kondratyev.currencygif.services.CurrencyGifService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CurrencyGifControllerTest {

    @Mock
    CurrencyGifService service;

    CurrencyGifController controller;
    MockMvc mockMvc;

    public static final String URL = "https://test.com";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        controller = new CurrencyGifController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(service.getGifUrlByCurrency(anyString()))
                .thenReturn(URL);
    }

    @Test
    void showWebPage() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("url"))
                .andExpect(model().attribute("url", URL));
    }

    @Test
    void showJson() throws Exception {

        mockMvc.perform(get("/rest")
                        .param("currency", "RUB"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.response", is(URL)));
    }

    @Test
    void showRedirect() throws Exception {

        mockMvc.perform(get("/redirect"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:" + URL));

        verify(service, times(1)).getGifUrlByCurrency(anyString());
    }
}