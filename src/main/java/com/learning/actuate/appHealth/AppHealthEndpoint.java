package com.learning.actuate.appHealth;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
// to expose api health  endpoint
@Endpoint(id = "/appHealth")
public class AppHealthEndpoint {

	   @ReadOperation
	    public AppHealth health() {
	        Map<String, Object> details = new LinkedHashMap<>();
	        details.put("Rate Management System", "is Active");
	        AppHealth health = new AppHealth();
	        health.setDetails(details);

	        return health;
	    }
}
