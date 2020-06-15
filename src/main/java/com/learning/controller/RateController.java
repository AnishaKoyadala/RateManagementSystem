
package com.learning.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learning.model.Rate;
import com.learning.service.RateService;

import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/rates")
public class RateController {

	private RateService rateService;
	@Autowired
	public RateController(RateService rateService){
		this.rateService = rateService;
	}
 
	@GetMapping(value = "/searchRate/{rateId}")
	public @ResponseBody ResponseEntity<JSONObject> searchRate(@PathVariable("rateId") Integer rateId) {
		final String uri = "https://surcharge.free.beeceptor.com/surcharge";
		JSONObject response = new JSONObject();
		RestTemplate restTemplate = new RestTemplate();
		// call to fetch VAT charge details
		ResponseEntity<String> surchargeData = restTemplate.getForEntity(uri, String.class);
		// search by rate id in database
		Optional<Rate> rateData = rateService.findById(rateId);
		// if rate id not found error message with status 404
		if (!rateData.isPresent()) {return new ResponseEntity("RateId not found in RMS", HttpStatus.NOT_FOUND);
		response.put("surchargeData", surchargeData);
		response.put("rateData", rateData);
		// output of all the fields of surcharge data and rate data in
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PostMapping("/addRate")
	public ResponseEntity<?> addRate(@RequestBody Rate rate) throws RuntimeException {
		try {
			Rate rateObj = new Rate();
			rateObj.setAmount(rate.getAmount());
			rateObj.setRateDescription(rate.getRateDescription());
			rateObj.setRateEffectiveDate(rate.getRateEffectiveDate());
			rateObj.setRateExpirationDate(rate.getRateExpirationDate());
			// store data in database
			rateService.insert(rateObj);
			return new ResponseEntity<Rate>(rateObj, HttpStatus.OK);
		} catch (Exception e) {
			// handle runtime exception with proper error message
			return new ResponseEntity("Internal server error. Please contact admin", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateRate/{id}")
	public ResponseEntity<?> updateRate(@PathVariable("id") Integer id, @RequestBody Rate rate)
			throws RuntimeException {
		try {
			Rate rateObj = rateService.findById(id).orElse(null);
			if (rateObj == null) {
				return new ResponseEntity<String>("No rate found", HttpStatus.NOT_MODIFIED);
			}
		
			rateObj.setRateId(id);
			rateObj.setAmount(rate.getAmount());
			rateObj.setRateDescription(rate.getRateDescription());
			rateObj.setRateEffectiveDate(rate.getRateEffectiveDate());
			rateObj.setRateExpirationDate(rate.getRateExpirationDate());
			// update data in db
			rateService.edit(rateObj);
			return new ResponseEntity<>(rateObj, HttpStatus.OK);
		} catch (Exception e) {
			// handle runtime exception with proper error message
			return new ResponseEntity("Internal server error. Please contact admin", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("/deleteRate/{id}")
	public ResponseEntity<String> deleteRate(@PathVariable("id") Integer id) {
		Rate rateObj = rateService.findById(id).orElse(null);
		if (rateObj == null) {
			// error message with not found status
			return new ResponseEntity<>("RateId not found in RMS", HttpStatus.NOT_FOUND);
		}
		// delete record based on id
		rateService.deleteById(id);
		return new ResponseEntity<String>("Rate Record removed", HttpStatus.OK);
	}
}