package ru.churkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.churkin.api.ISecurityService;

import java.security.Principal;
import java.util.logging.Logger;

@Service
public class SecurityService implements ISecurityService {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String findLoggedInUsername(){
        logger.info("---------------findLoggedInUsername start");
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("---------------findLoggedInUsername object : " + object);
        if (object instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) object;
            logger.info("---------------findLoggedInUsername UserDetails : " + userDetails);

            logger.info("---------------findLoggedInUsername username : " + userDetails.getUsername());
//            return ((UserDetails) userDetails).getUsername();
            return userDetails.getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

}
