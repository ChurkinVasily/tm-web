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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/task-save")
public class TaskSaveServlet extends HttpServlet {

    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository();
        projectRepository = new ProjectRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        String id = req.getParameter("taskId");
        Task task = taskRepository.findTaskById(id);
        task.setName(req.getParameter("taskName"));
        task.setDescription(req.getParameter("taskDescription"));
        final String projectName = req.getParameter("projectName");
        Project project = projectRepository.findProjectByName(projectName);
        String projectId = project.getId();

        task.setProjectId(projectId);
        task.setUserId(userId);

        taskRepository.updateTask(task);
        resp.sendRedirect("tasks");
    }
}
