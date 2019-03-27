package ru.churkin.controller.project;

import ru.churkin.api.IProjectRepository;
import ru.churkin.api.ITaskRepository;
import ru.churkin.entity.Project;
import ru.churkin.entity.Task;
import ru.churkin.repository.ProjectRepository;
import ru.churkin.repository.TaskRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        List<Project> projectsAll = projectRepository.getProjectAll();
//
        req.setAttribute("allProjects", projectsAll);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/project-list.jsp");
        requestDispatcher.forward(req, resp);


    }

}

