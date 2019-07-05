package demo.springone2018.saml.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.saml.provider.SamlServerConfiguration;
import org.springframework.security.saml.provider.service.config.SamlServiceProviderServerBeanConfiguration;

@Configuration
public class BeanConfig extends SamlServiceProviderServerBeanConfiguration {

	private static final Log logger = LogFactory.getLog(SamlServiceProviderServerBeanConfiguration.class);
	
	@Autowired
	private AppConfig appConfig;

	@Override
	protected SamlServerConfiguration getDefaultHostSamlServerConfiguration() {
		logger.info("Method getDefaultHostSamlServerConfiguration called, returning appConfig");
		return appConfig;
	}
}
