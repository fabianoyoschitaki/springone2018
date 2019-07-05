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

import static org.springframework.security.saml.provider.identity.config.SamlIdentityProviderSecurityDsl.identityProvider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.saml.provider.identity.config.SamlIdentityProviderSecurityConfiguration;

@EnableWebSecurity
public class IdentityProviderSecurityConfiguration {

	private static final Log logger = LogFactory.getLog(IdentityProviderSecurityConfiguration.class);
	
	@Configuration
	@Order(1)
	public static class SamlSecurity extends SamlIdentityProviderSecurityConfiguration {

		private final AppConfig appConfig;
		private final BeanConfig beanConfig;

		public SamlSecurity(BeanConfig beanConfig, @Qualifier("appConfig") AppConfig appConfig) {
			super("/saml/idp/", beanConfig);
			logger.info("SamlSecurity constructor called");
			this.appConfig = appConfig;
			this.beanConfig = beanConfig;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			logger.info("SamlSecurity configure called");
			super.configure(http);
			http
				.userDetailsService(beanConfig.userDetailsService()).formLogin();
			http.apply(identityProvider())
				.configure(appConfig);
		}
	}

	@Configuration
	public static class AppSecurity extends WebSecurityConfigurerAdapter {

		private final BeanConfig beanConfig;

		public AppSecurity(BeanConfig beanConfig) {
			logger.info("AppSecurity contructor called");
			this.beanConfig = beanConfig;
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			logger.info("AppSecurity configure called");
			http
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/**").authenticated()
				.and()
				.userDetailsService(beanConfig.userDetailsService())
				.formLogin()
				//.successForwardUrl("/")

			;
		}
	}
}
