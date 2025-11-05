package rae.yapily.processor_service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ProcessorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessorServiceApplication.class, args);
	}

}
