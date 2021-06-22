package com.ed.springsecurity.security;

import com.ed.springsecurity.constant.Permisson;
import com.ed.springsecurity.constant.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/students/**").hasRole(Role.STUDENT.name())
                .antMatchers(HttpMethod.DELETE,"/students/management/**").hasAuthority(Permisson.COURSE_WRITE.getPermisson())
                .antMatchers(HttpMethod.POST,"/students/management/**").hasAuthority(Permisson.COURSE_WRITE.getPermisson())
                .antMatchers(HttpMethod.PUT,"/students/management/**").hasAuthority(Permisson.COURSE_WRITE.getPermisson())
                .antMatchers(HttpMethod.GET,"/students/management/**").hasAnyRole(Role.ADMIN.name(),Role.ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails erdinc = User.builder()
                .username("erdi")
                .password(passwordEncoder.encode("35"))
                //.roles(Role.STUDENT.name())
                .authorities(Role.STUDENT.getGrantedAuthority())
                .build();

        UserDetails emre = User.builder()
                .username("emre")
                .password(passwordEncoder.encode("16"))
                //.roles(Role.ADMIN.name())
                .authorities(Role.ADMIN.getGrantedAuthority())
                .build();

        UserDetails fatih = User.builder()
                .username("fatih")
                .password(passwordEncoder.encode("34"))
                //.roles(Role.ADMINTRAINEE.name())
                .authorities(Role.ADMINTRAINEE.getGrantedAuthority())
                .build();

        return new InMemoryUserDetailsManager(
                erdinc,
                emre,
                fatih
        );
    }
}
