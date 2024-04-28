package com.xdisx.contract.app.rest;

import com.xdisx.contract.api.exception.ContractCreateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@ExtendWith(MockitoExtension.class)
class ContractControllerExceptionHandlerTest {
  @InjectMocks
  private ContractControllerExceptionHandler classUnderTest;


  @Test
  void handleContractCreateException() {
    ResponseEntity<Object> responseEntity = classUnderTest.handleContractCreateException(mock(WebRequest.class), mock(ContractCreateException.class));
    assertEquals(BAD_REQUEST, responseEntity.getStatusCode());
  }
}
