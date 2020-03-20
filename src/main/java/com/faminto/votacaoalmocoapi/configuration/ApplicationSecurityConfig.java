package com.faminto.votacaoalmocoapi.configuration;

import static com.faminto.votacaoalmocoapi.security.Permissao.ADMINISTRADOR;
import static com.faminto.votacaoalmocoapi.security.Permissao.APURADOR;
import static com.faminto.votacaoalmocoapi.security.Permissao.FAMINTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.httpBasic();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.inMemoryAuthentication()
				.withUser("admin").password(passwordEncoder().encode("123")).authorities(ADMINISTRADOR.name(), APURADOR.name())
				.and()
				.withUser("apurador").password(passwordEncoder().encode("123")).authorities(APURADOR.name())
				.and()
				.withUser("faminto_1").password(passwordEncoder().encode("123")).authorities(FAMINTO.name())
				.and()
				.withUser("faminto_2").password(passwordEncoder().encode("123")).authorities(FAMINTO.name())
				.and()
				.withUser("faminto_3").password(passwordEncoder().encode("123")).authorities(FAMINTO.name());
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
