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
import java.util.logging.Logger;

@Controller
public class UserControllerSecure {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    IUserService userService;

    @GetMapping(value = "/ma")
    public String mainPage(Model model) {

        return "mainXXX";
    }

}
