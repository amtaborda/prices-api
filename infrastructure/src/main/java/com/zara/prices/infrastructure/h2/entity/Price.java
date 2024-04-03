package com.zara.prices.infrastructure.h2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "price")
public class Price {

    @Id
    private Integer priceId;

    private Integer brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "price_list")
    private Rate priceList;

    private Integer productId;

    private Integer priority;

    private Double price;

    private String curr;
}
