package com.zara.prices.application.exception;


public class BadRequestException extends GenericException {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;


    /**
     * Constructor for BadRequestException.
     *
     * @param description a {@link String} object.
     */
    public BadRequestException(String description) {
        super(description);
    }
}
