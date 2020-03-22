package com.faminto.votacaoalmocoapi.configuration;

import static com.faminto.votacaoalmocoapi.security.Permissao.ROLE_ADMINISTRADOR;
import static com.faminto.votacaoalmocoapi.security.Permissao.ROLE_APURADOR;
import static com.faminto.votacaoalmocoapi.security.Permissao.ROLE_FAMINTO;

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
			.antMatchers("/api/usuarios/**").hasAnyAuthority(ROLE_ADMINISTRADOR.name(), ROLE_ADMINISTRADOR.name())
			.antMatchers("/api/restaurantes/**").hasAnyAuthority(ROLE_ADMINISTRADOR.name(), ROLE_ADMINISTRADOR.name())
			.antMatchers("/api/eleicoes/**").hasAnyAuthority(ROLE_ADMINISTRADOR.name(), ROLE_APURADOR.name())
			.antMatchers("/api/votacoes/**").hasAnyAuthority(ROLE_ADMINISTRADOR.name(), ROLE_FAMINTO.name())
			.anyRequest().permitAll()
			.and()
			.httpBasic();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.inMemoryAuthentication()
				.withUser("admin").password(passwordEncoder().encode("123")).authorities(ROLE_ADMINISTRADOR.name(), ROLE_APURADOR.name())
				.and()
				.withUser("apurador").password(passwordEncoder().encode("123")).authorities(ROLE_APURADOR.name())
				.and()
				.withUser("faminto_1").password(passwordEncoder().encode("123")).authorities(ROLE_FAMINTO.name())
				.and()
				.withUser("faminto_2").password(passwordEncoder().encode("123")).authorities(ROLE_FAMINTO.name())
				.and()
				.withUser("faminto_3").password(passwordEncoder().encode("123")).authorities(ROLE_FAMINTO.name())
				.and()
				.withUser("faminto_4").password(passwordEncoder().encode("123")).authorities(ROLE_FAMINTO.name())
				.and()
				.withUser("faminto_5").password(passwordEncoder().encode("123")).authorities(ROLE_FAMINTO.name())
				.and()
				.withUser("faminto_6").password(passwordEncoder().encode("123")).authorities(ROLE_FAMINTO.name())
				.and()
				.withUser("faminto_7").password(passwordEncoder().encode("123")).authorities(ROLE_FAMINTO.name())
				.and()
				.withUser("faminto_8").password(passwordEncoder().encode("123")).authorities(ROLE_FAMINTO.name())
				.and()
				.withUser("faminto_9").password(passwordEncoder().encode("123")).authorities(ROLE_FAMINTO.name())
				.and()
				.withUser("faminto_10").password(passwordEncoder().encode("123")).authorities(ROLE_FAMINTO.name());
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
