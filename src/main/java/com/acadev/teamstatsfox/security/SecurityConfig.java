package com.acadev.teamstatsfox.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.acadev.teamstatsfox.security.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final CustomUserDetailsService userDetailsService;
	private final JwtAuthorizationFilter jwtAuthorizationFilter;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService,
			JwtAuthorizationFilter jwtAuthorizationFilter) {
		this.userDetailsService = customUserDetailsService;
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder)
			throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
		return authenticationManagerBuilder.build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.requestMatchers("/public/**", "/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/v2/api-docs/**",
						"/swagger-resources/**")
				.permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	/**
	 * @Autowired private CustomUserDetailsService customerDetailsService;
	 * 
	 * @Autowired private JwtFilter jwtFilter;
	 * 
	 * @Bean public PasswordEncoder paswoEncoder() { return
	 *       NoOpPasswordEncoder.getInstance(); }
	 * 
	 * @Bean protected SecurityFilterChain securityFilterChain2(HttpSecurity
	 *       httpSecurity) throws Exception {
	 *       httpSecurity.cors().configurationSource(request -> new
	 *       CorsConfiguration().applyPermitDefaultValues()) .and()
	 *       .csrf().disable() .authorizeHttpRequests() .requestMatchers(
	 *       "/public/**", "/auth/**", "/v3/api-docs/**", "/swagger-ui/**",
	 *       "/v2/api-docs/**", "/swagger-resources/**") .permitAll() .anyRequest()
	 *       .authenticated() .and().exceptionHandling() .and().sessionManagement()
	 *       .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 *       httpSecurity.addFilterBefore(jwtFilter,
	 *       UsernamePasswordAuthenticationFilter.class); return
	 *       httpSecurity.build(); }
	 * 
	 * @Bean public AuthenticationManager
	 *       authenticationManager(AuthenticationConfiguration
	 *       authenticationConfiguration) throws Exception { return
	 *       authenticationConfiguration.getAuthenticationManager(); }
	 */

}
