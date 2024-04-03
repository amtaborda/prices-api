package com.zara.prices.infrastructure.h2.adapter;

import com.zara.prices.domain.PriceDto;
import com.zara.prices.infrastructure.h2.adapter.util.MockUtil;
import com.zara.prices.infrastructure.h2.mapper.PriceMapper;
import com.zara.prices.infrastructure.h2.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceH2AdapterTest {

    @InjectMocks
    PriceH2Adapter priceH2Adapter;

    @Mock
    private PriceRepository pricesRepository;

    @Mock
    private PriceMapper priceMapper;

    @Test
    void getPricesTest() {
        when(pricesRepository.findPrices(any(), any(), any())).thenReturn(MockUtil.getMockListPriceEntity());
        when(priceMapper.totDtoList(any())).thenReturn(MockUtil.getMockListPriceDto());

        List<PriceDto> prices = priceH2Adapter.findPrices(any(), any(), any());

        assertEquals(1, prices.size());
        verify(pricesRepository, times(1)).findPrices(any(), any(), any());
    }
}