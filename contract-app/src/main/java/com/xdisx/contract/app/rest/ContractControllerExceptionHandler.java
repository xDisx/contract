package com.xdisx.contract.app.rest;

import com.xdisx.contract.api.exception.ContractCreateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(assignableTypes = ContractController.class)
public class ContractControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContractCreateException.class)
    public ResponseEntity<Object> handleContractCreateException(WebRequest r, ContractCreateException e) {
        return setResponse(r, e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> setResponse(WebRequest r, ContractCreateException e, HttpStatus status) {
        ProblemDetail body = ProblemDetail.forStatusAndDetail(status, e.getMessage());

        return new ResponseEntity<>(body, status);
    }
}
