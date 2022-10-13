package com.jun.nautilus.server.mvc.config

import com.jun.nautilus.server.mvc.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.server.WebFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    ){

    @Bean
    public fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.headers().frameOptions().disable()

        http.csrf()
            .disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
            .antMatchers("/api/notifications/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }





}