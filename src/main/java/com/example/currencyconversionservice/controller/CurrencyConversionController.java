package com.example.currencyconversionservice.controller;

import com.example.currencyconversionservice.model.CurrencyConversionDto;
import com.example.currencyconversionservice.service.CurrencyConvertionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {
    private final CurrencyConvertionService currencyConvertionService;

    public CurrencyConversionController(CurrencyConvertionService currencyConvertionService) {
        this.currencyConvertionService = currencyConvertionService;
    }

    @Operation(
            summary = "Convert currency",
            description = "Do currency exchange")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Currency converted",
                    content = @Content(schema = @Schema(implementation = CurrencyConversionDto.class))
            )
    })
    @GetMapping("/cc/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionDto convertCurrency(@PathVariable String from, @PathVariable String to,
                                                 @PathVariable BigDecimal quantity) {
        return currencyConvertionService.convertCurrency(from, to, quantity);
    }
}
