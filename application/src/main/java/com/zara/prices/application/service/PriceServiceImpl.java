package com.zara.prices.application.service;

import com.zara.prices.application.ports.rest.PriceService;
import com.zara.prices.application.ports.spi.PricePersistence;
import com.zara.prices.domain.PriceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    PricePersistence priceRepository;

    public PriceServiceImpl(PricePersistence priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<PriceDto> getPrice(LocalDateTime date, Integer productId, Integer brandId) {
        log.debug("PriceServiceImpl - getPrice({}, {}, {})", date, productId, brandId);

        List<PriceDto> prices = priceRepository.findPrices(date, productId, brandId);
        log.debug("PriceServiceImpl - getPrice - prices list: {}", prices);

        if (CollectionUtils.isEmpty(prices)) {
            return Optional.empty();
        }

        return prices.stream().findFirst();
    }
}
