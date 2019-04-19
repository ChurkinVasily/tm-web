package ru.churkin.controller;

import com.sun.faces.action.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.churkin.api.IUserService;
import ru.churkin.entity.Result;
import ru.churkin.entity.User;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class UserControllerSecure {

    @Autowired
    IUserService userService;


//    @GetMapping(value = "/ma")
//    public String mainPage(Model model) {
//
//        return "index";
//    }
//
//    @GetMapping(value = "/loginSecure")
////    @PreAuthorize("!isAuthenticated()")
//    public String check(Model model) {
//        return "login";
//    }
//
//    @GetMapping(value = "/mainXXX")
////    @PreAuthorize("isAuthenticated()")
//    public String user() {
//        return "mainXXX";
//    }

}
