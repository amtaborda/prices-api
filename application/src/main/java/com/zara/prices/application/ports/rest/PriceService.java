package com.zara.prices.application.ports.rest;

import com.zara.prices.domain.PriceDto;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

    /**
     * Retrieve a final price
     *
     * @param date      application date
     * @param productId product identifier
     * @param brandId   brand identifier
     * @return Optional of {@link PriceDto} object
     */
    public Optional<PriceDto> getPrice(LocalDateTime date, Integer productId, Integer brandId);
}
