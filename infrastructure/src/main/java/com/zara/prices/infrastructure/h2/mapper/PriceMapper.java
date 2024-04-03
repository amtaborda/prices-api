package com.zara.prices.infrastructure.h2.mapper;

import com.zara.prices.domain.PriceDto;
import com.zara.prices.infrastructure.h2.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "priceList.rate", target = "rate")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "entity", target = "price", qualifiedByName = "calculateFinalPrice")
    PriceDto toDto(Price entity);

    List<PriceDto> totDtoList(List<Price> list);

    @Named("calculateFinalPrice")
    default Double calculateFinalPrice(Price entity) {
        if (Objects.isNull(entity.getPriceList()) || entity.getPriceList().getRate() == 0.0) {
            return entity.getPrice();
        } else {
            BigDecimal bd = BigDecimal.valueOf(entity.getPrice() * entity.getPriceList().getRate());
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
    }
}
