package ru.churkin.controller.task;

import ru.churkin.entity.Task;
import ru.churkin.repository.TaskRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/tasks-for-project")
public class TaskGetByProject extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        String projectId = req.getParameter("id");
        List<Task> tasksByProjectId = taskRepository.getByProjectId(projectId, userId);
        req.setAttribute("allTasks", tasksByProjectId);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/task-list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
