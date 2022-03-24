package bcp.exchange.rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import bcp.exchange.rate.repository.ExchangeRateRepository;

@EnableResourceServer //Anotacion agregada para que pueda trabajar con Oauth2
@SpringBootApplication
public class ApiBcpExchangeRateApplication {
	
	@Autowired
	ExchangeRateRepository exchangeRateRepo;

	public static void main(String[] args) {
		SpringApplication.run(ApiBcpExchangeRateApplication.class, args);
	}

}
