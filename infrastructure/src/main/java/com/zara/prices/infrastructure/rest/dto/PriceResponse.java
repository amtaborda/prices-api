package com.zara.prices.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PriceResponse {

    /**
     * product identifier
     */
    private final Integer productId;

    /**
     * brand identifier
     */
    private final Integer brandId;

    /**
     * applied rate
     */
    private final Double rate;

    /**
     * price start date
     */
    private final LocalDateTime startDate;

    /**
     * price end date
     */
    private final LocalDateTime endDate;

    /**
     * final price
     */
    private final Double price;
}
