package ru.churkin.controller;

import ru.churkin.entity.User;
import ru.churkin.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/create-user")
public class CreateUserServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String userPass = req.getParameter("userPass");
        boolean isNewUser = !(userName == null) && !userName.isEmpty() && (userRepository.findUserByName(userName) == null);
        boolean isCorrectPass = !(userPass == null) && !(userPass.isEmpty());
        if (isNewUser && isCorrectPass) {
            User user = new User(userName, userPass);
            userRepository.createUser(user);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
