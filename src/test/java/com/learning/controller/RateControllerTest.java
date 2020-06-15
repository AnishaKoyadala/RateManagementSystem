package com.learning.controller;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.learning.model.Rate;
import com.learning.service.RateService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(MockitoJUnitRunner.class)
public class RateControllerTest {

	@Mock
	private RateService rateService;

	private MockMvc mockMvc;

	private static String CONTENTS;
	private static Rate rateData;
	private static Date todayDate;

	@Before
	public void setUp() throws Exception {
		SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		todayDate = oldFormat.parse("2020-06-11 11:11:12");

		JSONObject response = new JSONObject();
		JSONObject rateObject = new JSONObject();
		rateObject.put("rateID", 2);
		rateObject.put("rateDescription", "rate value");
		rateObject.put("rateEffectiveDate", todayDate);
		rateObject.put("rateExpirationDate", todayDate);
		rateObject.put("amount", 20);
		JSONObject surchargeObject = new JSONObject();
		surchargeObject.put("surchargeRate", 100);
		surchargeObject.put("surchargeDescription", "VAT");
		response.put("surchargeData", surchargeObject);
		response.put("rateData", rateObject);
		rateData= new Rate(2, "rate value", todayDate, todayDate, 20);
		CONTENTS = response.toString();

		mockMvc = MockMvcBuilders.standaloneSetup(new RateController(rateService)).build();

	}

	@Test
	public void searchRate() throws Exception {
		mockMvc.perform(get("/searchRate/{rateId}","rateId"));
		ArgumentCaptor<Integer> searchArgument = ArgumentCaptor.forClass(Integer.class);
		verify(rateService, times(1)).findById(searchArgument.capture());
		Integer id = searchArgument.getValue();
		assertEquals(id,"rateId");

	}

	@Test
		public void postObject() throws Exception {
			mockMvc.perform(post("/addRate/")
					.contentType(MediaType.TEXT_PLAIN)
					.content(CONTENTS));
			
	        verify(rateService, times(1)).insert(rateData);
			assertEquals(rateData.getAmount(),20);
			}

	@Test
	public void deleteObject() throws Exception {
		mockMvc.perform(delete("/deleteRate/{id}", "id1"));
		ArgumentCaptor<Integer> deleteArgument = ArgumentCaptor.forClass(Integer.class);
		verify(rateService, times(1)).deleteById(deleteArgument.capture());
		Integer id = deleteArgument.getValue();
		assertEquals(id,"id1");
	}

	@Test
	public void putObject() throws Exception {
		mockMvc.perform(put("/updateRate/{id}", "id1").contentType(MediaType.TEXT_PLAIN_VALUE).content(CONTENTS));
		verify(rateService, times(1)).edit(rateData);
		assertEquals("2", rateData.getRateId());
	}

}
