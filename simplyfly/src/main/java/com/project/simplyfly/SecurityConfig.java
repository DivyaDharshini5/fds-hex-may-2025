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
import org.springframework.web.bind.annotation.GetMapping;





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
						//customer
						.requestMatchers("/api/customer/add").permitAll()
						.requestMatchers("/api/customer/get-one").hasAuthority("CUSTOMER")
						.requestMatchers("/api/customer/get-all").permitAll()
						.requestMatchers("/api/booking/customer").hasAuthority("CUSTOMER")
						.requestMatchers("/api/customer/update").authenticated()
						.requestMatchers("/api/customer/delete").authenticated()
						//passenger
						.requestMatchers("/api/passenger/add").hasAuthority("CUSTOMER")
						.requestMatchers("/api/passenger/update").authenticated()
						.requestMatchers("/api/passenger/delete").authenticated()
						//flight
						.requestMatchers("/api/flight/add").hasAuthority("OWNER")
						.requestMatchers("/api/route/add").hasAnyAuthority("OWNER","SUPERVISOR")
						.requestMatchers("/api/owner/add").permitAll()
						.requestMatchers("/api/owner/get-one").hasAuthority("OWNER")
						.requestMatchers("/api/flightroute/add/{flightId}/{routeId}").hasAnyAuthority("OWNER","SUPERVISOR")
						.requestMatchers("/api/flight/search/route/{flightId}/{routeId}").permitAll()
						.requestMatchers("/api/flight/delete/{flightId}").hasAuthority("OWNER")
						.requestMatchers("/api/flight/get-all").permitAll()
						.requestMatchers("/api/route/edit/{routeId}").permitAll()
						
						.requestMatchers("/api/flight/search/customer").hasAuthority("CUSTOMER")
						.requestMatchers("/api/seat/available").hasAuthority("CUSTOMER")
						//seat
						.requestMatchers("/api/seat/select").hasAuthority("CUSTOMER")
						
						.requestMatchers("/api/coupon/add").permitAll()
						.requestMatchers("/api/booking/makeBooking").authenticated()
						
						.requestMatchers("/api/booking/get-all").hasAuthority("SUPERVISOR")
						.requestMatchers("/a/ownerpi/booking").hasAuthority("SUPERVISOR")
						
						
						
						.requestMatchers("/api/supervisor/add").authenticated()
						//payment
						.requestMatchers("/api/payment/process").hasAuthority("CUSTOMER")
						.requestMatchers("/api/payment/get").hasAuthority("CUSTOMER")
						.requestMatchers("/api/payment/cancel/{bookingId}").hasAuthority("CUSTOMER")
						.requestMatchers("/api/payment/refund/{bookingId}").hasAuthority("OWNER")
						//Token
						.requestMatchers("/api/booking/owner").authenticated()
						
						
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
