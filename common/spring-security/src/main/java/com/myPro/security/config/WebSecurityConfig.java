package com.myPro.security.config;


//import com.myPro.security.custom.UserDetailsService;

import com.myPro.security.custom.CustomMd5PasswordEncoder;
import com.myPro.security.filter.TokenAuthenticationFilter;
import com.myPro.security.filter.TokenLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("all")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder customMd5PasswordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //关闭csrf跨站请求伪造
                .csrf().disable()
                .formLogin().disable()
                .logout().disable()

                //开启跨域
//                .cors().and()
//                .authorizeRequests().and()
                //禁用session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                        .requestMatchers(HttpMethod.POST,"/admin/system/index/login").permitAll()
                        .anyRequest().authenticated())
//                .authenticationProvider(authenticationProvider())
                .addFilterBefore(new TokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new TokenLoginFilter(authenticationManager()));
        return http.build();

    }



    //注册自定义用户登录信息查询Bean
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userDetailsService.loadUserByUsername(username);
    }


    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(customMd5PasswordEncoder);
        return new ProviderManager(provider);
    }




//    private AuthenticationManager myAuthenticationManager;

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        myAuthenticationManager = config.getAuthenticationManager();
//        return config.getAuthenticationManager();
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        // DaoAuthenticationProvider 从自定义的 userDetailsService.loadUserByUsername 方法获取UserDetails
//        authProvider.setUserDetailsService(userDetailsService());
//        // 设置密码编辑器
//        authProvider.setPasswordEncoder(customMd5PasswordEncoder);
//        return authProvider;
//    }






}
