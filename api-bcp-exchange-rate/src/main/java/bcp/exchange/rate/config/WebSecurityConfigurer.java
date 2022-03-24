package bcp.exchange.rate.config;

import java.util.Arrays;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//Clase agregada para que pueda trabajar con Oauth2
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Bean
	  public FilterRegistrationBean corsFilter() {
	    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(corsConfigurationSource()));
	    bean.setOrder(SecurityProperties.DEFAULT_FILTER_ORDER);
	    return bean;
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	  CorsConfiguration configuration = new CorsConfiguration();
	  configuration.setAllowedOrigins(Arrays.asList("*"));
	  configuration.setAllowedMethods(Arrays.asList("*"));
	  configuration.setAllowedHeaders(Arrays.asList("*"));

	  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	  source.registerCorsConfiguration("/**", configuration);
	  return source;
	}
}
