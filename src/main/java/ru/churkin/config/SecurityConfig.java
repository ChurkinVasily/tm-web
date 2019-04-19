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

import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                ;
        auth.inMemoryAuthentication()
                .withUser("test").password(passwordEncoder().encode("test")).roles(Role.ADMIN.name())
                .and()
                .withUser("us").password(passwordEncoder().encode("us")).roles(Role.USER.name())
                ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
//                    .antMatchers("/login*").permitAll()
                    .antMatchers("/project-list").hasRole(Role.ADMIN.name())
                    .anyRequest().hasAnyRole(Role.USER.name(), Role.ADMIN.name())
//                .anyRequest().permitAll()
                .anyRequest().authenticated()

                    .and()
                .formLogin()
//                    .loginPage("/login")
////                    /// j_check - указать в jsp <c:url value=""
//                    .loginProcessingUrl("/j_check")
////    //                .failureUrl("/index.jsp")
////                    .failureUrl("/ma")
////                    // j_username и j-password - параметры логина и пароля из формы
//                    .defaultSuccessUrl("/task-list")
//                    .usernameParameter("j_userName")
//                    .passwordParameter("j_password")
//                    .permitAll()
                    .and()
                .logout().permitAll()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login.jsp")
                    .invalidateHttpSession(true)
                    .and()
                .csrf().disable();
    }
}
