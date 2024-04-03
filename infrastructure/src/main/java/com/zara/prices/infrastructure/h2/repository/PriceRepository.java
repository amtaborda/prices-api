package com.zara.prices.infrastructure.h2.repository;

import com.zara.prices.infrastructure.h2.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

    @Query("SELECT p FROM Price p " +
            "WHERE :date BETWEEN p.startDate AND p.endDate AND p.productId = :productId AND p.brandId = :brandId " +
            "ORDER BY p.priority ASC")
    List<Price> findPrices(@Param("date") LocalDateTime date, @Param("productId") Integer productId, @Param("brandId") Integer brandId);
}