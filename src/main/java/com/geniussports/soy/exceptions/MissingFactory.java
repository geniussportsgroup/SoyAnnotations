package com.geniussports.soy.exceptions;


import static java.lang.String.format;

public class MissingFactory extends RuntimeException {

    public static final String MESSAGE_FORMAT = "Cannot find factory for %s";

    public MissingFactory(Class<?> classObject) {
        this(format(MESSAGE_FORMAT, classObject.getName()));
    }

    public MissingFactory() {
    }

    public MissingFactory(String message) {
        super(message);
    }

    public MissingFactory(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingFactory(Throwable cause) {
        super(cause);
    }

    public MissingFactory(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
