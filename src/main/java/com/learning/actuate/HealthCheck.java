package com.learning.actuate;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator{


    @Override
    public Health health() {
        return Health.up().withDetail("Rate Management System", "is Active").build();
    }
}
