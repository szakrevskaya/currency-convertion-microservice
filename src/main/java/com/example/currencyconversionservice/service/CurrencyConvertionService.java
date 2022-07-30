package com.example.currencyconversionservice.service;

import com.example.currencyconversionservice.model.CurrencyConversionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConvertionService {
    private static String FOREX_SERVICE_URL = "http://localhost:8000/ce/from/{from}/to/{to}";

    public CurrencyConversionDto convertCurrency(String from, String to, BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionDto> responseEntity = new RestTemplate().getForEntity(
                FOREX_SERVICE_URL, CurrencyConversionDto.class,
                uriVariables);
        CurrencyConversionDto response = responseEntity.getBody();
        response.setQuantity(quantity);
        response.setTotalCalculatedAmount(quantity.multiply(response.getConversionMultiple()));

       return response;
    }
}
