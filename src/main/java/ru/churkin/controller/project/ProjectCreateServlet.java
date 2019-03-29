package ru.churkin.controller.project;

import ru.churkin.repository.ProjectRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/create-project")
public class ProjectCreateServlet extends HttpServlet {

    private ProjectRepository projectRepository;

    @Override
    public void init() throws ServletException {
        projectRepository = new ProjectRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("projectName");
        projectRepository.createProject(name);
        resp.sendRedirect("projects");
    }
}
