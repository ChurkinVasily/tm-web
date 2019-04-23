package ru.churkin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.churkin.enums.Role;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private UserDetailsService userDetailsService;

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
                .antMatchers("/").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .antMatchers("/welcome").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .antMatchers("/admin", "/project-list", "/user-list").hasRole(Role.ADMIN.name())


                    .antMatchers("/login").permitAll()
                .anyRequest().permitAll()
                .anyRequest().authenticated()

                    .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/welcome")
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")




//                .formLogin()
////                    .loginPage("/login")
//////                    /// j_check - указать в jsp <c:url value=""
////                    .loginProcessingUrl("/check")
////                    .failureUrl("/index.jsp")
////                    .failureUrl("/ma")
//                .defaultSuccessUrl("/ma")
//////                    // j_username и j-password - параметры логина и пароля из формы
////                    .usernameParameter("j_userName")
////                    .passwordParameter("j_password")
                    .permitAll()
                    .and()
                .logout().permitAll()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .and()
                .csrf().disable();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}
