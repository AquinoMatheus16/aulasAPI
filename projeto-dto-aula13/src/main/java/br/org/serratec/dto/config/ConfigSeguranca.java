package br.org.serratec.dto.config;
//https://www.youtube.com/watch?v=7HQ-x9aoZx8 RESOLVER ESSE 'ERRO'

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ConfigSeguranca extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	auth.inMemoryAuthentication()
//	.withUser("teste")
//	.password("{noop}123456")
//	.roles("ADMIN")
//	.and().withUser("matheus")
//	.password("{noop}654321")
//	.roles("USER", "ADMIN");
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService)
	.passwordEncoder(bCryptPasswordEncoder());
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	http.authorizeHttpRequests()
//	.antMatchers("/api/funcionarios").permitAll()
//	.anyRequest().authenticated()
//	.and()
//	.httpBasic()
//	.and()
//	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	.and()
//	.cors()
//	.and()
//	.csrf().disable();
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.antMatchers("/", "/public/**").permitAll()
		.antMatchers("/funcionarios").permitAll()
		.antMatchers("/funcionarios/salarios-por-idade").permitAll()
		.antMatchers(HttpMethod.GET, "/funcionarios/salario", "/funcionarios/pagina", "/funcionarios/nome").hasAnyAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/usuarios").hasAnyAuthority("ADMIN","USER")
		.antMatchers(HttpMethod.POST, "/usuarios").hasAuthority("ADMIN");
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//	CorsConfiguration corsConfiguration = new CorsConfiguration();
//	corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
//	corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
//	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
//	return source;
//	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
	return new BCryptPasswordEncoder();
	}
	
}
