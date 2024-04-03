package com.zara.prices.application.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    /**
     * message
     */
    private String message;

    /**
     * Constructor
     *
     * @param message description
     */
    public Error(String message) {
        this.message = message;
    }
}
