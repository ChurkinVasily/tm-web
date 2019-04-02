package ru.churkin.controller.project;

import ru.churkin.api.IProjectRepository;
import ru.churkin.api.IUserRepository;
import ru.churkin.entity.Project;
import ru.churkin.repository.ProjectRepository;
import ru.churkin.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/projects")
public class ProjectAllServlet extends HttpServlet {

    private IProjectRepository projectRepository;

    @Override
    public void init() throws ServletException {
        projectRepository = new ProjectRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        final String userId = (String) session.getAttribute("userId");
        final String userName = (String) session.getAttribute("userName");

        if (userId == null || userId.isEmpty()) {
            resp.sendRedirect("login");
            return;
        }

        List<Project> projectsAll = projectRepository.getProjectAll();
        req.setAttribute("allProjects", projectsAll);
        req.setAttribute("currentUserName", userName);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/project-list.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

