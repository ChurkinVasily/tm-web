package ru.churkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.churkin.api.IUserService;
import ru.churkin.entity.User;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserControllerSecure {

    @Autowired
    IUserService userService;

    @GetMapping(value = "/ma")
    public String mainPage(Model model) {

        return "index";
    }

    @GetMapping(value = "/j_check")
//    @PreAuthorize("!isAuthenticated()")
    public String check(Model model) {
        return "loginSecure";
    }
}
