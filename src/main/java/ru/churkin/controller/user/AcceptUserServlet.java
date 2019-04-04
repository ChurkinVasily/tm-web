package ru.churkin.controller.user;

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

@WebServlet(urlPatterns = "/accept")
public class AcceptUserServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = new UserRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPass");

        if (userRepository.validate(userName, userPassword)) {
            User user = userRepository.findUserByName(userName);
            HttpSession session = req.getSession();
            String userId = user.getId();
            session.setAttribute("userId", userId);
            session.setAttribute("userName", userName);
            resp.sendRedirect("tasks");
        } else {
            resp.sendRedirect("login");
        }
    }
}
