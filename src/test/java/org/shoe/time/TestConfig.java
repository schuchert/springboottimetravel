package org.shoe.time;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestPropertySource;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

@Configuration
@TestPropertySource(properties="spring.main.allow-bean-definition-overriding=true")
public class TestConfig {
    @Bean
    @Primary
    public Clock systemClock() {
        return Clock.fixed(
                Instant.parse("2031-08-22T10:00:00Z"),
                ZoneOffset.UTC);
    }
}
