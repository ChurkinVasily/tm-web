package ru.churkin.controller.task;

import ru.churkin.api.ITaskRepository;
import ru.churkin.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/task-remove")
public class TaskRemoveServlet extends HttpServlet {

    private ITaskRepository taskRepository;

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String taskId = req.getParameter("id");
        taskRepository.deleteTaskById(taskId);
        resp.sendRedirect("tasks");
    }
}
