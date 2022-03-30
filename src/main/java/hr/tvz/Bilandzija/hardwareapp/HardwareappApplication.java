package hr.tvz.Bilandzija.hardwareapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HardwareappApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardwareappApplication.class, args);
	}

}
