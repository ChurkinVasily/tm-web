package ru.churkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.churkin.api.IUserRepository;
import ru.churkin.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    IUserRepository userRepository;


    @GetMapping(value = "/main1")
    public String mainPage(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String userName = null;
        if (!(session == null)) {
            String userId = (String) session.getAttribute("userId");
            User user = userRepository.findUserById(userId);
            if (user == null) {
                userName = null;
            } else {
                userName = user.getName();
            }
        }
        model.addAttribute("currentUserName", userName);
        model.addAttribute("session", session);
        return "main";
    }

    @GetMapping(value = "/login1")
    public String loginUser(HttpServletRequest req, Model model) {
        return "login";
    }

    @GetMapping(value = "/reg1")
    public String regUser(HttpServletRequest req, Model model) {
        return "reg";
    }

    @PostMapping(value = "/accept1")
    public String acceptUser(HttpServletRequest req, Model model) {
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPass");

        if (userRepository.validate(userName, userPassword)) {
            User user = userRepository.findUserByName(userName);
            HttpSession session = req.getSession();
            String userId = user.getId();
            session.setAttribute("userId", userId);
            session.setAttribute("userName", userName);
            return "redirect:" + "tasks1";
        } else {
            return "redirect:" + "login1";
        }
    }

    @PostMapping(value = "/create-user1")
    public String createUser(HttpServletRequest req, Model model) {

        String userName = req.getParameter("userName");
        boolean isNewUser = true;
        boolean isCorrectPass = true;
        if (userName == null || userName.isEmpty()) {
            isNewUser = false;
        }
        if (!(userRepository.findUserByName(userName) == null)) {
            isNewUser = false;
        }

        String userPass = req.getParameter("userPass");
        if (userPass == null || userPass.isEmpty()) {
            isCorrectPass = false;
        }
        if (isNewUser && isCorrectPass) {
            User user = new User(userName, userPass);
            userRepository.createUser(user);
            return "redirect:" + "login";
        }
        return "redirect:" + "reg1";
    }

    @GetMapping(value = "/logout1")
    public String logoutUser(HttpServletRequest req) {
        req.getSession().invalidate();
        return "redirect:" + "main1";
    }


}
