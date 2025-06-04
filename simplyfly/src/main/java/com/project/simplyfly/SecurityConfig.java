package com.project.simplyfly;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;





	@Configuration //<- This ensures that this class gets called during every API call
	public class SecurityConfig {
		@Autowired
		private JwtFilter jwtFilter;
	    
		@Bean
		 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
				.csrf((csrf) -> csrf.disable()) 
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/api/user/signup").permitAll()
						.requestMatchers("/api/customer/add").permitAll()
						.requestMatchers("/api/customer/get-one").hasAuthority("CUSTOMER")
						.requestMatchers("/api/flight/add").hasAuthority("OWNER")
						.requestMatchers("/api/route/add").hasAnyAuthority("OWNER","SUPERVISOR")
						.requestMatchers("/api/owner/add").permitAll()
						.requestMatchers("/api/owner/get-one").hasAuthority("OWNER")
						.requestMatchers("/api/flightroute/add/{flightId}/{routeId}").hasAnyAuthority("OWNER","SUPERVISOR")
						.requestMatchers("/api/flight/search/route/{flightId}/{routeId}").permitAll()
						.requestMatchers("/api/customer/get-all").permitAll()
						.requestMatchers("/api/coupon/add").permitAll()
						.requestMatchers("/api/booking/add/{customerId}/{flightId}/{couponId}").permitAll()
						.requestMatchers("/api/user/token").authenticated()
						.requestMatchers("/api/booking/get-all").hasAuthority("SUPERVISOR")
						.requestMatchers("/api/booking/customer").hasAuthority("CUSTOMER")
						.requestMatchers("/api/supervisor/add").hasAuthority("SUPERVISOR")
						.requestMatchers("/api/payment/add/{bookingId}").permitAll()
						.requestMatchers("/api/payment/get-one/{customerId}").hasAuthority("CUSTOMER")
						.requestMatchers("/api/payment/get-all").hasAnyAuthority("OWNER","SUPERVISOR")
						.requestMatchers("/api/add/passenger").authenticated()
						.requestMatchers("api/selectseat?flightId=1&seatNumber=12A&passengerId=1").permitAll()
						
						
				.anyRequest().authenticated()
				)
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) 
			 .httpBasic(Customizer.withDefaults()); //<- this activated http basic technique
			return http.build();
		}
		
		@Bean
		 PasswordEncoder passwordEncoder() {  //<- Bean saves this object in spring's context
			return new BCryptPasswordEncoder();
		}
		
		@Bean
		 AuthenticationManager getAuthManager(AuthenticationConfiguration auth) 
				throws Exception {
			  return auth.getAuthenticationManager();
		 }
}
