package ru.churkin.controller.task;

import ru.churkin.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/create-task")
public class TaskCreateServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("taskName");
        taskRepository.createTask(name);
        resp.sendRedirect("tasks");
    }

}
