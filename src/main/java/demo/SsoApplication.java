package demo;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;
import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

@SpringBootApplication
@EnableOAuth2Sso
public class SsoApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SsoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SsoApplication.class);
	}


	/**
	 * Required to fine-tune the OAuth 2 SSO behavior.
	 */
	@Configuration
	protected static class OauthConfig extends OAuth2SsoConfigurerAdapter {

		/**
		 *	We want the "protected" resource to require OAuth 2 SSO.
		 */
		@Override
		public void match(RequestMatchers matchers)
		{
			matchers
					.antMatchers("/doclist/**")
					.antMatchers("/getDocument/**");
		}


		/**
		 *	We want the "unprotected" resource to require no authentication at all.
		 */
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
					.authorizeRequests()
					.antMatchers("/getDocument/**").authenticated()
					.antMatchers("/doclist/**").authenticated()
					.antMatchers("/unprotected/**").permitAll()
			;
		}
	}
}
