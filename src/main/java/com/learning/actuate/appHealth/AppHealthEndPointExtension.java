package com.learning.actuate.appHealth;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;

// creates an extension for AppHealthEndpoint
@EndpointWebExtension(endpoint = AppHealthEndpoint.class)
public class AppHealthEndPointExtension {
	 private final AppHealthEndpoint delegate;

	    public AppHealthEndPointExtension(AppHealthEndpoint delegate) {
	        this.delegate = delegate;
	    }

	    @ReadOperation
	    public WebEndpointResponse<AppHealth> getHealth() {
	        AppHealth health = delegate.health();
	        return new WebEndpointResponse<>(health, 200);
	    }
}
