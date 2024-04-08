package com.zara.prices.infrastructure.rest.controller;

import com.zara.prices.application.exception.BadRequestException;
import com.zara.prices.application.exception.GenericException;
import com.zara.prices.application.ports.rest.PriceService;
import com.zara.prices.infrastructure.h2.adapter.util.MockUtil;
import com.zara.prices.infrastructure.rest.dto.PriceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @InjectMocks
    PriceController priceController;

    @Mock
    PriceService apiService;

    @Test
    public void getPricesTestOK() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(apiService.getPrice(any(), any(), any())).thenReturn(MockUtil.getMockOptionalPriceDto());

        ResponseEntity<PriceResponse> responseEntity = priceController.getPrices(
                LocalDateTime.of(2020, 06, 14, 18, 0, 0), 1, 1);

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
        assertThat(responseEntity.getBody().getPrice()).isEqualTo(40.82);
    }

    @Test
    public void getPricesTestNoFound() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(apiService.getPrice(any(), any(), any())).thenReturn(Optional.empty());

        ResponseEntity<PriceResponse> responseEntity = priceController.getPrices(
                LocalDateTime.of(2020, 06, 14, 18, 0, 0), 1, 1);

        assertThat(responseEntity.getStatusCode().value()).isEqualTo(404);
    }

    @Test
    void getPriceWithOutMinRangeDateTest() {
        // Application date valid range from 2020-01-01 00:00 to now
        // date=2000-06-14T18:00:00
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        try {
            priceController.getPrices(
                    LocalDateTime.of(2000, 6, 14, 18, 0, 0), 1, 1);
            fail();
        } catch (GenericException ex) {
            assertTrue(ex instanceof BadRequestException);
            assertThat(ex.getDescription()).isEqualTo("The application date must be greater than 2020-01-01 00:00:00");
        }
    }

    @Test
    void getPriceWithOutMaxRangeDateTest() {
        // Application date valid range from 2020-01-01 00:00 to now
        // date=2024-06-14T10:00:00
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        try {
            priceController.getPrices(
                    LocalDateTime.of(2024, 6, 14, 18, 0, 0), 1, 1);
            fail();
        } catch (GenericException ex) {
            assertTrue(ex instanceof BadRequestException);
            assertThat(ex.getDescription()).isEqualTo("The application date must be less than or equal to this moment");
        }
    }

    @Test
    void getPriceWithWrongProductIdTest() {
        // The productId must be greater than zero
        // productId=-1
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        try {
            priceController.getPrices(
                    LocalDateTime.of(2024, 6, 14, 18, 0, 0), -1, 1);
            fail();
        } catch (GenericException ex) {
            assertTrue(ex instanceof BadRequestException);
            assertThat(ex.getDescription()).isEqualTo("The productId must be greater than zero");
        }
    }

    @Test
    void getPriceWithWrongBrandIdTest() {
        // The brandId must be greater than zero
        // brandId=-1
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        try {
            priceController.getPrices(
                    LocalDateTime.of(2024, 6, 14, 18, 0, 0), 1, -1);
            fail();
        } catch (GenericException ex) {
            assertTrue(ex instanceof BadRequestException);
            assertThat(ex.getDescription()).isEqualTo("The brandId must be greater than zero");
        }
    }
}