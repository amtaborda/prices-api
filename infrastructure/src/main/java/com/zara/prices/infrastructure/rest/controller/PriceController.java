package com.zara.prices.infrastructure.rest.controller;

import com.zara.prices.application.exception.BadRequestException;
import com.zara.prices.application.ports.rest.PriceService;
import com.zara.prices.domain.PriceDto;
import com.zara.prices.infrastructure.rest.dto.PriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.zara.prices.infrastructure.rest.util.Constants.PRICES_PATH;
import static com.zara.prices.infrastructure.rest.util.Constants.ZARA_PRICES_PATH;

@Slf4j
@RestController
@RequestMapping(ZARA_PRICES_PATH + PRICES_PATH)
public class PriceController {

    PriceService apiService;

    public PriceController(PriceService apiService) {
        this.apiService = apiService;
    }

    /**
     * Retrieve a final price by params
     *
     * @param date      application date
     * @param productId product identifier
     * @param brandId   brand identifier
     * @return ResponseEntity of {@link PriceResponse} object
     */
    @GetMapping
    public ResponseEntity<PriceResponse> getPrices(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") final LocalDateTime date,
                                                   @RequestParam final Integer productId,
                                                   @RequestParam final Integer brandId) {
        validateParams(date, productId, brandId);

        Optional<PriceDto> price = apiService.getPrice(date, productId, brandId);
        log.debug("Controller - getPrice: {}", price);

        if (price.isPresent()) {
            return ResponseEntity.ok().body(PriceResponse.builder()
                    .price(price.get().getPrice())
                    .productId(price.get().getProductId())
                    .brandId(price.get().getBrandId())
                    .rate(price.get().getRate())
                    .endDate(price.get().getEndDate())
                    .startDate(price.get().getStartDate()).build());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * validate input params
     *
     * @param date      application date
     * @param productId product identifier
     * @param brandId   brand identifier
     * @throws {@link BadRequestException} exception
     */
    private void validateParams(LocalDateTime date, Integer productId, Integer brandId) {
        log.debug("Controller - validateParams - date={} - productId={} - brandId={})", date, productId, brandId);
        if (productId <= 0) {
            throw new BadRequestException("The productId must be greater than zero");
        }

        if (brandId <= 0) {
            throw new BadRequestException("The brandId must be greater than zero");
        }

        if (date.isBefore(LocalDateTime.of(2020, 01, 01, 0, 0, 0))) {
            throw new BadRequestException("The application date must be greater than 2020-01-01 00:00:00");
        }

        if (date.isAfter(LocalDateTime.now())) {
            throw new BadRequestException("The application date must be less than or equal to this moment");
        }
    }
}