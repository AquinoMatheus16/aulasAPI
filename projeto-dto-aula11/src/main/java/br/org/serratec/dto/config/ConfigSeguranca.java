package br.org.serratec.dto.config;
//https://www.youtube.com/watch?v=7HQ-x9aoZx8 RESOLVER ESSE 'ERRO'

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class ConfigSeguranca extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("teste")
		.password("{noop}123456")
		.roles("ADMIN")
		.and().withUser("matheus")
		.password("{noop}654321")
		.roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.authorizeHttpRequests()
	.antMatchers("/funcionarios").permitAll()
	.anyRequest().authenticated()
	.and()
	.httpBasic()
	.and()
	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
