package org.shoe.time;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

@SpringBootTest(classes = TestConfig.class)
class TimeControlledTest {
    @Autowired
    Clock systemClock;

    @Test
    void timeIsControlled() {
        Date justNewDate = new Date();
        System.out.println(justNewDate);

        java.util.Date clockDirect = Date.from(systemClock.instant());
        System.out.println(clockDirect);

        java.util.Date viaInstant = Date.from(Instant.now(systemClock));
        System.out.println(viaInstant);

        System.out.println(Instant.now());
    }
}
