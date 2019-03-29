package ru.churkin.controller.task;

import ru.churkin.entity.Project;
import ru.churkin.entity.Task;
import ru.churkin.repository.ProjectRepository;
import ru.churkin.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/task-save")
public class TaskSaveServlet extends HttpServlet {

    Logger log = Logger.getLogger(this.getClass().getName());


    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository();
        projectRepository = new ProjectRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Project> projects = projectRepository.getProjectAll();

        String id = req.getParameter("taskId");
        Task task = taskRepository.findTaskById(id);
        task.setName(req.getParameter("taskName"));
        task.setDescription(req.getParameter("taskDescription"));
        final String projectName = req.getParameter("projectName");
        Project project = projectRepository.findProjectByName(projectName);
        String projectId = project.getId();

        task.setProjectId(projectId);

        taskRepository.updateTask(task);
        resp.sendRedirect("tasks");
    }
}
