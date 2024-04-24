package org.raduromaniuc.birds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//todo docker-compose must run maven first to build the target (otherwise ill have to put that in github also)
		SpringApplication.run(Application.class, args);
	}

}
