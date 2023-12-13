package com.example.currencyconversionservice.service;

import com.example.currencyconversionservice.model.CurrencyConversionDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrencyConvertionService {

    private static final String USD = "USD";
    private static final String EUR = "EUR";
    private static final String BYR = "BYR";
    private static final BigDecimal USD_CONVERSION_MULTIPLY = BigDecimal.valueOf(3.162);
    private static final BigDecimal EUR_CONVERSION_MULTIPLY = BigDecimal.valueOf(3.412);

    public CurrencyConversionDto convertCurrency(String from, String to, BigDecimal quantity) {
        BigDecimal conversionMultiple = BigDecimal.valueOf(3.162);
        if (from.equals(USD) && to.equals(BYR)) {
            return CurrencyConversionDto.builder()
                    .from(USD)
                    .to(BYR)
                    .quantity(quantity)
                    .conversionMultiple(USD_CONVERSION_MULTIPLY)
                    .totalCalculatedAmount(quantity.multiply(conversionMultiple)).build();
        }

        if (from.equals(EUR) && to.equals(BYR)) {
            return CurrencyConversionDto.builder()
                    .from(EUR)
                    .to(BYR)
                    .quantity(quantity)
                    .conversionMultiple(EUR_CONVERSION_MULTIPLY)
                    .totalCalculatedAmount(quantity.multiply(conversionMultiple)).build();
        }

        if (from.equals(BYR) && to.equals(USD)) {
            return CurrencyConversionDto.builder()
                    .from(BYR)
                    .to(USD)
                    .quantity(quantity)
                    .conversionMultiple(USD_CONVERSION_MULTIPLY)
                    .totalCalculatedAmount(quantity.divide(conversionMultiple, RoundingMode.HALF_UP)).build();
        }

        if (from.equals(BYR) && to.equals(EUR)) {
            return CurrencyConversionDto.builder()
                    .from(BYR)
                    .to(EUR)
                    .quantity(quantity)
                    .conversionMultiple(EUR_CONVERSION_MULTIPLY)
                    .totalCalculatedAmount(conversionMultiple.divide(quantity, RoundingMode.HALF_UP)).build();
        }

        return new CurrencyConversionDto();
    }
}
