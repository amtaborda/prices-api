package com.zara.prices.application.exception;

public class GenericException extends RuntimeException {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -6788661443546955762L;

    private String description = null;

    /**
     * Constructor for GenericException.
     *
     * @param description a {@link String} object.
     */
    public GenericException(String description) {
        this.description = description;
    }

    /**
     * Getter for the field <code>description</code>.
     *
     * @return a {@link String} object.
     */
    public String getDescription() {
        return description;
    }
}
