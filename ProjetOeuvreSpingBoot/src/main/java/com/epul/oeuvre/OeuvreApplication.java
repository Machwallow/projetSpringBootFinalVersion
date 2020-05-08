package com.epul.oeuvre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OeuvreApplication extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//setRegisterErrorPageFilter(false);
		return application.sources(OeuvreApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(OeuvreApplication.class, args);
	}
}

