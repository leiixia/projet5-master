package fr.leahiff.opeclassrooms.project6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;

@SpringBootApplication
public class Project6Application {

	static Logger logger = LoggerFactory.getLogger(Project6Application.class);

	public static void main(String[] args) throws MalformedURLException {
		logger.info("Application Running");
		SpringApplication.run(Project6Application.class, args);
	}

}
