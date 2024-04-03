package com.zara.prices.infrastructure.h2.adapter;

import com.zara.prices.application.ports.spi.PricePersistence;
import com.zara.prices.domain.PriceDto;
import com.zara.prices.infrastructure.h2.mapper.PriceMapper;
import com.zara.prices.infrastructure.h2.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PriceH2Adapter implements PricePersistence {

    private PriceRepository pricesRepository;
    private PriceMapper priceMapper;

    public PriceH2Adapter(PriceRepository pricesRepository, PriceMapper priceMapper) {
        this.pricesRepository = pricesRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public List<PriceDto> findPrices(LocalDateTime date, Integer productId, Integer brandId) {
        return priceMapper.totDtoList(pricesRepository.findPrices(date, productId, brandId));
    }
}
