package ru.churkin.controller;

import ru.churkin.entity.User;
import ru.churkin.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/main")
public class MainPageServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

        req.setAttribute("currentUserName", userName);
        req.setAttribute("session", session);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views//main.jsp");
        requestDispatcher.forward(req, resp);
    }
}
