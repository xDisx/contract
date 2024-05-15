package com.xdisx.contract.app.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xdisx.contract.api.dto.ContractStatusDto;
import com.xdisx.contract.api.dto.request.ContractPageRequestDto;
import com.xdisx.contract.api.dto.request.UpdateContractStatusRequestDto;
import com.xdisx.contract.api.dto.response.ContractPageResponseDto;
import com.xdisx.contract.api.dto.response.ContractResponseDto;
import com.xdisx.contract.app.mock.ContractMock;
import com.xdisx.contract.app.util.FileReadUtil;
import com.xdisx.customer.api.dto.response.CustomerResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.xdisx.contract.api.dto.request.ContractCreateRequestDto;
import com.xdisx.contract.app.service.ContractService;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ContractControllerTest {
  private static final ObjectMapper mapper =
          new ObjectMapper()
                  .registerModule(new JavaTimeModule())
                  .setDateFormat(new SimpleDateFormat("dd.MM.yyyy"));
  private static final String CONTRACT_PATH = "/xdisx/contract";
  private static final String CONTRACTS_PATH = "/xdisx/contracts";
  private static final String CONTRACT_RESPONSE_JSON = "ContractResponse.json";
  private static final String CONTRACTS_RESPONSE_JSON = "ContractsResponse.json";

  @Mock
  private ContractService contractService;

  @InjectMocks
  private ContractController classUnderTest;
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
            new MappingJackson2HttpMessageConverter();
    mappingJackson2HttpMessageConverter.setObjectMapper(mapper);

    mockMvc =
            MockMvcBuilders.standaloneSetup(classUnderTest)
                    .setMessageConverters(mappingJackson2HttpMessageConverter)
                    .setControllerAdvice(ContractControllerExceptionHandler.class)
                    .build();
  }


  @Test
  void createContract() throws Exception {
    ContractCreateRequestDto contractRequest = ContractMock.getCreateContractRequest();
    ContractResponseDto response = ContractMock.getContractResponse();

    when(contractService.createContract(contractRequest)).thenReturn(response);

    var apiResponse =
            mockMvc
                    .perform(
                            post(CONTRACT_PATH)
                                    .content(mapper.writeValueAsString(contractRequest))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

    var expectedResponse = FileReadUtil.readResourceAsString(CONTRACT_RESPONSE_JSON);
    JSONAssert.assertEquals(expectedResponse, apiResponse, JSONCompareMode.LENIENT);
  }

  @Test
  void getContracts() throws Exception {
    ContractPageResponseDto responseDto = ContractMock.getContractPageResponse();
    when(contractService.findContracts(any(ContractPageRequestDto.class))).thenReturn(responseDto);

    var apiResponse =
            mockMvc
                    .perform(
                            get(CONTRACTS_PATH)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

    assertNotNull(apiResponse);
    var expectedResponse = FileReadUtil.readResourceAsString(CONTRACTS_RESPONSE_JSON);
    JSONAssert.assertEquals(expectedResponse, apiResponse, JSONCompareMode.LENIENT);
  }

  @Test
  void getContract() throws Exception {
    ContractResponseDto responseDto = ContractMock.getContractResponse();
    when(contractService.getContract(responseDto.getID())).thenReturn(responseDto);

    var apiResponse =
            mockMvc
                    .perform(
                            get(CONTRACTS_PATH + "/" + responseDto.getID())
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

    assertNotNull(apiResponse);
    var expectedResponse = FileReadUtil.readResourceAsString(CONTRACT_RESPONSE_JSON);
    JSONAssert.assertEquals(expectedResponse, apiResponse, JSONCompareMode.LENIENT);
    }

  @Test
  void updateContractStatus() throws Exception {
    ContractResponseDto responseDto = ContractMock.getContractResponse();
    responseDto.setContractStatus(ContractStatusDto.ACTIVE);
    when(contractService.updateContractStatus(eq(responseDto.getID()), argThat(arg -> ContractStatusDto.ACTIVE.equals(arg.getNewStatus())))).thenReturn(responseDto);

    var apiResponse =
            mockMvc
                    .perform(
                            put(CONTRACTS_PATH + "/" + responseDto.getID())
                                    .content(mapper.writeValueAsString(UpdateContractStatusRequestDto.builder().newStatus(ContractStatusDto.ACTIVE).build()))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

    assertNotNull(apiResponse);
    var expectedResponse = FileReadUtil.readResourceAsString(CONTRACT_RESPONSE_JSON);
    JSONAssert.assertEquals(expectedResponse, apiResponse, JSONCompareMode.LENIENT);
    }
}
