package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.demo.service.UserServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserServiceImp customUserDetailsService;

	@Autowired
	AuthenticationSuccessHandler customsuccessHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

		return daoAuthenticationProvider;
	}

	protected void configure(AuthenticationManagerBuilder daoAuthenticationProvider) throws Exception {

		daoAuthenticationProvider.authenticationProvider(authenticationProvider());

	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests(
						(authorize) -> authorize.requestMatchers(new AntPathRequestMatcher("/registration**"))
								.permitAll().requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
								.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
								.requestMatchers(new AntPathRequestMatcher("/console/**")).permitAll()
								.requestMatchers(new AntPathRequestMatcher("/adminScreen**")).hasRole("ADMIN")
								.requestMatchers(new AntPathRequestMatcher("/home**")).hasRole("USER")
								.requestMatchers(new AntPathRequestMatcher("/login**")).permitAll()
								.requestMatchers(new AntPathRequestMatcher("/View_user**")).permitAll()

				).formLogin(form -> form.loginPage("/login").successHandler(customsuccessHandler).permitAll())
				.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
						.permitAll()

				);
		http.headers().frameOptions().disable();

		return http.build();
	}
}
