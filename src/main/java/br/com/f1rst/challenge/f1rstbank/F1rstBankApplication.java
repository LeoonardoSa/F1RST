package br.com.f1rst.challenge.f1rstbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class F1rstBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(F1rstBankApplication.class, args);
	}

}
