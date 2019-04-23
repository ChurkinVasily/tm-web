package ru.churkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.churkin.api.ISecurityService;
import ru.churkin.api.IUserService;
import ru.churkin.entity.User;
import ru.churkin.service.UserValidator;

import java.util.logging.Logger;

@Controller
public class UserControllerSecure {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private IUserService userService;

    @Autowired
    private ISecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "sec/registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("userForm") User userForm,
                               BindingResult bindingResult,
                               Model model) {
        logger.info("----------------------registration start");
        userValidator.validate(userForm, bindingResult);
        logger.info("----------------------registration after validate");

        if (bindingResult.hasErrors()) {
            return "sec/registration";
        }

        userService.createNewUser(userForm.getName(), userForm.getPassword());

        securityService.autoLogin(userForm.getName(), userForm.getPassword());

        return "redirect:" + "sec/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "sec/login";
    }

    @GetMapping(value = {"/", "/welcome"})
    public String welcome(Model model) {
        return "sec/welcome";
    }

    @GetMapping(value = "/admin")
    public String admin(Model model) {
        return "sec/admin";
    }

}
