package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import demo.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
				.authenticationProvider(authenticationProvider());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

	    DaoAuthenticationProvider provider =
	            new DaoAuthenticationProvider(customUserDetailsService);

	    provider.setPasswordEncoder(passwordEncoder());

	    return provider;
	}
}