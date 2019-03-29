package ru.churkin.controller.task;

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

@WebServlet(urlPatterns = "/task-edit")
public class TaskEditServlet extends HttpServlet {

    private TaskRepository taskRepository;

    private ProjectRepository projectRepository;

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository();
        projectRepository = new ProjectRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskId = req.getParameter("id");
        Task task = taskRepository.findTaskById(taskId);
        req.setAttribute("task", task);

        Project currentProject = projectRepository.findProjectById(task.getProjectId());
        req.setAttribute("currentProject", currentProject);

        List<Project> allProjects = projectRepository.getProjectAll();
        req.setAttribute("allProjects", allProjects);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/task-edit.jsp");
        requestDispatcher.forward(req, resp);
    }
}
