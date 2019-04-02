package ru.churkin.controller.project;

import ru.churkin.entity.Project;
import ru.churkin.repository.ProjectRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/project-edit")
public class ProjectEditServlet extends HttpServlet {

    private ProjectRepository projectRepository;

    @Override
    public void init() throws ServletException {
        projectRepository = new ProjectRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectId = req.getParameter("id");
        Project project = projectRepository.findProjectById(projectId);

        req.setAttribute("project", project);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/project-edit.jsp");
        requestDispatcher.forward(req, resp);
    }

}
