package com.zara.prices.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PriceDto {

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
