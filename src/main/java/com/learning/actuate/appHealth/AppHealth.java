package com.learning.actuate.appHealth;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.Data;
// Contains information about exposed health status fields
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AppHealth {

	private Map<String, Object> details;

	@JsonAnyGetter
	public Map<String, Object> getDetails() {
		return this.details;
	}

	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}
	
}
