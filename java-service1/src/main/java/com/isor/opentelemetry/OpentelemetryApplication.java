package com.isor.opentelemetry;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpentelemetryApplication {

	public static void main(String[] args) {
		var app = new SpringApplication(OpentelemetryApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
