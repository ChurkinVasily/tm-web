package ru.churkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.churkin.api.IUserService;
import ru.churkin.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class UserController {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    IUserService userService;

    @GetMapping(value = "/main")
    public String mainPage(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String userName = null;
        if (!(session == null)) {
            String userId = (String) session.getAttribute("userId");
            User user = userService.findUserById(userId);
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

    ////////// -----------------убрать лишнюю букву N в конце слова /login
    @GetMapping(value = "/loginn")
    public String loginUser(HttpServletRequest req, Model model) {
        return "login";
    }

    @GetMapping(value = "/reg")
    public String regUser(HttpServletRequest req, Model model) {
        return "reg";
    }

    @PostMapping(value = "/accept")
    public String acceptUser(HttpServletRequest req, Model model) {
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPass");

        if (userService.validate(userName, userPassword)) {
            User user = userService.findUserByName(userName);
            HttpSession session = req.getSession();
            String userId = user.getId();
            session.setAttribute("userId", userId);
            session.setAttribute("userName", userName);
            return "redirect:" + "tasks";
        } else {
            return "redirect:" + "login";
        }
    }

    @PostMapping(value = "/create-user")
    public String createUser(HttpServletRequest req, Model model) {

        String userName = req.getParameter("userName");
        boolean isNewUser = true;
        boolean isCorrectPass = true;
        if (userName == null || userName.isEmpty()) {
            isNewUser = false;
        }
        if (!(userService.findUserByName(userName) == null)) {
            isNewUser = false;
        }

        String userPass = req.getParameter("userPass");
        if (userPass == null || userPass.isEmpty()) {
            isCorrectPass = false;
        }
        if (isNewUser && isCorrectPass) {
            User user = new User(userName, userPass);
            userService.createNewUser(userName, userPass);
            return "redirect:" + "login";
        }
        return "redirect:" + "reg";
    }

    /// ----------не нужно после введения Spring Security

//    @GetMapping(value = "/logout")
//    public String logoutUser(HttpServletRequest req, HttpServletResponse res) {
////        req.getSession().invalidate();
////        return "redirect:" + "main";
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(req, res, auth);
//        }
//        return "redirect:/login?logout";
//    }
}
