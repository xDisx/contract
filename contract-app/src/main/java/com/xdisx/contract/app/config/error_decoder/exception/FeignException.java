package com.xdisx.contract.app.config.error_decoder.exception;

public class FeignException extends RuntimeException {
    public FeignException(String message) {
        super(message);
    }
}
