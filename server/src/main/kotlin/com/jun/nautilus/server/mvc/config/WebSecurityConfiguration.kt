package com.jun.nautilus.server.mvc.config

import com.jun.nautilus.server.mvc.config.WebSecurityConfiguration.MyCustomDsl.Companion.customDsl
import com.jun.nautilus.server.mvc.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableWebSecurity
class WebSecurityConfiguration
{

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer? {
        return WebSecurityCustomizer { web: WebSecurity -> web.ignoring().antMatchers("/static/**") }
    }


    @Bean
    public fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.headers().frameOptions().disable()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/auth/**", "/view/**", "/error", "/","/h2-console/**","/api/notifications/list/**","/api/notifications/*","/notification/*").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
            .apply(customDsl())









        return http.build()
    }

    class MyCustomDsl : AbstractHttpConfigurer<MyCustomDsl, HttpSecurity>() {
        override fun configure(builder: HttpSecurity?) {
            val authenticationManager = builder!!.getSharedObject(AuthenticationManager::class.java)
            builder!!.addFilterAt(JwtAuthenticationFilter(authenticationManager), BasicAuthenticationFilter::class.java)

        }

        companion object{
            fun customDsl(): MyCustomDsl? {
                return MyCustomDsl()
            }
        }

    }






}