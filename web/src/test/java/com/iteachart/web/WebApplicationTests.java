package com.iteachart.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@ComponentScan(basePackages = {"com.iteachart.model", "com.iteachart.load", "com.iteachart.web"})
class WebApplicationTests {

}
