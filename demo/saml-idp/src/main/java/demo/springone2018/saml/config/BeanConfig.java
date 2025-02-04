/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package demo.springone2018.saml.config;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.saml.provider.SamlServerConfiguration;
import org.springframework.security.saml.provider.identity.config.SamlIdentityProviderServerBeanConfiguration;

@Configuration
public class BeanConfig extends SamlIdentityProviderServerBeanConfiguration {
	private static final Log logger = LogFactory.getLog(SamlIdentityProviderServerBeanConfiguration.class);
	private final AppConfig config;

	public BeanConfig(AppConfig config) {
		logger.info("Construtor called");
		this.config = config;
	}

	@Override
	protected SamlServerConfiguration getDefaultHostSamlServerConfiguration() {
		logger.info("getDefaultHostSamlServerConfiguration called");
		return config;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		logger.info("userDetailsService called");
		UserDetails userDetails = User.withDefaultPasswordEncoder()
			.username("fabiano")
			.password("password")
			.roles("USER")
			.build();
		return new InMemoryUserDetailsManager(userDetails);
	}
}
