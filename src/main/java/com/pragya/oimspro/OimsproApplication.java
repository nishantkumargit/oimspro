package com.pragya.oimspro;

import com.pragya.oimspro.mqtt.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OimsproApplication {

	public static void main(String[] args) {
		SpringApplication.run(OimsproApplication.class, args);
	}

}
