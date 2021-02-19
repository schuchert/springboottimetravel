package org.shoe.time;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.Clock;

@RestController
public class HelloComponent {
    private final Clock systemClock;

    public HelloComponent(Clock systemClock) {
        this.systemClock = systemClock;
    }

    @GetMapping("/hello")
    public String message() {
        return "Hello, it is now: " + Date.from(systemClock.instant());
    }
}
