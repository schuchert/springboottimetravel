package org.shoe.time;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(classes = {HelloComponent.class, TestConfig.class})
class HelloComponentTest {
    @Autowired
    HelloComponent hello;

    @Test
    void messageControlled() {
        String message = hello.message();
        assertThat(message).contains("2031");
    }

}
