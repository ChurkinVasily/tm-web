package ru.churkin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.churkin.enums.Role;
import ru.churkin.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("pass1")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("pass2")).roles("ADMIN")
                ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/admin").hasRole(Role.ADMIN.name())
                .anyRequest().hasAnyRole(Role.USER.name(), Role.ADMIN.name())

                .and()
                .formLogin()
                .loginPage("/loginSecure.jsp")
                /// j_check - указать в jsp <c:url value=""
                .loginProcessingUrl("/j_check")
//                .failureUrl("/index.jsp")
                .failureUrl("/ma")
                // j_username и j-password - параметры логина и пароля изф формы
                .usernameParameter("j_userName")
                .passwordParameter("j_password")
                .permitAll()
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/loginSecure.jsp")
                .and()
                .csrf().disable();
    }
}
