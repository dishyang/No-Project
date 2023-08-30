package com.example.noProject.config.security;



import com.example.noProject.config.security.filter.TokenSecurityFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.HeaderWriterFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Configuration
public class SpringSecurityConfig {

    @Resource
    TokenSecurityFilter tokenSecurityFilter;
    @Resource
    AuthenticationEntryPoint authenticationEntryPoint;

    /*@Resource
    AccessDeniedHandler accessDeniedHandler;*/

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 由于前后端分离，csrf和session都可以关闭
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //设置放行和认证
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry.requestMatchers("/v3/api-docs", "/v3/api-docs.yaml","/swagger-ui/*","/v3/api-docs/swagger-config","/user/login").permitAll();
            authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();//开启所有请求都要认证
        });
        //添加自定义的Token认证过滤器
        http.addFilterAfter(tokenSecurityFilter, HeaderWriterFilter.class);
        //设置认证（异常）失败处理
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(authenticationEntryPoint));
        //设置鉴权（异常）失败处理。不生效，被统一异常处理捕抓到
        //http.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(accessDeniedHandler))
        return http.build();
    }

}
