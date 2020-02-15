package com.weather.api.weather_api.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = {"classpath:workflow-config.xml"})
public class AppConfig {

}
