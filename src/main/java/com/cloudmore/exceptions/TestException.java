package com.cloudmore.exceptions;

import java.io.Serial;
public class TestException extends RuntimeException {

    /**
     * Customized exception handling
     */
    @Serial private static final long serialVersionUID = 1L;

    public TestException(String message) {
        super(message);
    }
}