package ru.churkin.controller.project;

import ru.churkin.entity.Project;
import ru.churkin.repository.ProjectRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/project-save")
public class ProjectSaveServlet extends HttpServlet {

    Logger log = Logger.getLogger(this.getClass().getName());

    private ProjectRepository projectRepository;

    @Override
    public void init() throws ServletException {
        projectRepository = new ProjectRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("projectId");
        Project project = projectRepository.findProjectById(id);

        project.setName(req.getParameter("projectName"));
        project.setDescription(req.getParameter("projectDescription"));
        project.setTimeStart(req.getParameter("projectDateStart"));
        project.setTimeFinish(req.getParameter("projectDateFin"));

        projectRepository.updateProject(project);
        resp.sendRedirect("projects");
    }
}

