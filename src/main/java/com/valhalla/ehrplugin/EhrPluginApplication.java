package com.valhalla.ehrplugin;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		@PropertySource(value = "file:./.env", ignoreResourceNotFound = true)
})
public class EhrPluginApplication {
	// Load .env file
	Dotenv dotenv = Dotenv.load();

	// Retrieve values
	String apiUrl = dotenv.get("API_URL");
	String authorizationHeader = dotenv.get("AUTHORIZATION_HEADER");
	public static void main(String[] args) {
		SpringApplication.run(EhrPluginApplication.class, args);
	}
}
