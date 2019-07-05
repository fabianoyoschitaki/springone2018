package demo.springone2018.saml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SamlIdpApplication {

	private static final Log logger = LogFactory.getLog(SamlIdpApplication.class);
	
	public static void main(String[] args) {
		logger.info("IdP Application started...");
		SpringApplication.run(SamlIdpApplication.class, args);
	}
}
