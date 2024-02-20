package com.exercises.theatresmanager.configs;

import com.exercises.theatresmanager.convertors.ScreenInfoToTheatreScreen;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TheatreManagementWebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		//registry.addConverter(new ScreenInfoToTheatreScreen());
	}

}
