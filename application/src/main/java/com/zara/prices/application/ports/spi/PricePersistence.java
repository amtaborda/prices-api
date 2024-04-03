package com.zara.prices.application.ports.spi;

import com.zara.prices.domain.PriceDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PricePersistence {

    /**
     * finds a final price list ordered by priority
     *
     * @param date      application date
     * @param productId product identifier
     * @param brandId   brand identifier
     * @return List of {@link PriceDto} object
     */
    List<PriceDto> findPrices(LocalDateTime date, Integer productId, Integer brandId);
}
