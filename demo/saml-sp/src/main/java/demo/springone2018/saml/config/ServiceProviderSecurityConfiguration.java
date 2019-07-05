package demo.springone2018.saml.config;

import static org.springframework.security.saml.provider.service.config.SamlServiceProviderSecurityDsl.serviceProvider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.saml.provider.service.config.SamlServiceProviderSecurityConfiguration;

@EnableWebSecurity
public class ServiceProviderSecurityConfiguration {

	private static final Log logger = LogFactory.getLog(ServiceProviderSecurityConfiguration.class);
	
	@Configuration
	@Order(1)
	public static class SamlSecurity extends SamlServiceProviderSecurityConfiguration {
		private AppConfig appConfig;

		public SamlSecurity(BeanConfig beanConfig, AppConfig appConfig) {
			super(beanConfig);
			logger.info("SamlSecurity constructor called");
			this.appConfig = appConfig;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			logger.info("SamlSecurity configure called");
			super.configure(http);
			http.apply(serviceProvider())
				.configure(appConfig);
		}
	}

	@Configuration
	public static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			logger.info("ApplicationSecurity configure called");
			http
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/**").authenticated()
				.and()
				.formLogin().loginPage("/saml/sp/select");
		}
	}
}
