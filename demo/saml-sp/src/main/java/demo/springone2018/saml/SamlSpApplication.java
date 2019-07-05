package demo.springone2018.saml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SamlSpApplication {

	private static final Log logger = LogFactory.getLog(SamlSpApplication.class);
	
	public static void main(String[] args) {
		logger.info("SP Application started...");
		SpringApplication.run(SamlSpApplication.class, args);
	}
}
