package ru.churkin.controller;

import ru.churkin.api.ITaskRepository;
import ru.churkin.entity.Task;
import ru.churkin.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TaskServlet extends HttpServlet {

    private ITaskRepository taskRepository;

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Task> taskAll = taskRepository.getTaskAll();
        req.setAttribute("allTasks", taskAll);

        req.getRequestDispatcher("task-list.jsp");

    }

}
