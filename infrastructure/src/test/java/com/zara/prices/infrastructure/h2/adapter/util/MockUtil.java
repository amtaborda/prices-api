package com.zara.prices.infrastructure.h2.adapter.util;

import com.zara.prices.domain.PriceDto;
import com.zara.prices.infrastructure.h2.entity.Price;
import com.zara.prices.infrastructure.h2.entity.Rate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MockUtil {


    public static Price getMockPriceEntity() {
        return Price.builder()
                .priceId(1)
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 06, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 06, 14, 0, 0, 0))
                .price(35.5)
                .curr("EUR")
                .priority(0)
                .priceList(Rate.builder().rateId(1).rate(1.15).build())
                .build();
    }

    public static List<Price> getMockListPriceEntity() {
        return Arrays.asList(getMockPriceEntity());
    }

    public static Optional<PriceDto> getMockOptionalPriceDto() {
        return Optional.of(PriceDto.builder()
                .brandId(1)
                .startDate(LocalDateTime.of(2020, 06, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 06, 14, 0, 0, 0))
                .price(40.82)
                .rate(1.15)
                .build());
    }

    public static List<PriceDto> getMockListPriceDto() {
        return Arrays.asList(getMockOptionalPriceDto().get());
    }
}
