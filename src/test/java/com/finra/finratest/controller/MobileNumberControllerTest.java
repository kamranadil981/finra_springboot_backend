package com.finra.finratest.controller;

import com.finra.finratest.helper.GenerateCombinations;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class MobileNumberControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    GenerateCombinations generateCombinations;
    @Test
    public void getMobileNumberCombinationTestInvalidInput()throws Exception {//for invalid input
        String sampleInput = "123";
        MvcResult mvcResult = this.mockMvc.perform(get("/finratest/" + sampleInput)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals("Invalid Mobile Number!", response.getContentAsString());
    }
    @Test
    public void getMobileNumberCombinationTestValidInput()throws Exception {//for valid input
        String sampleInput = "1234567";
        MvcResult mvcResult = this.mockMvc.perform(get("/finratest/" + sampleInput)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals("1457",new JSONObject(response.getContentAsString()).getString("count"));
    }

}

