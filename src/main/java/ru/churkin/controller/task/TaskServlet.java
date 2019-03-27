package ru.churkin.controller.task;

import ru.churkin.api.ITaskRepository;
import ru.churkin.entity.Task;
import ru.churkin.repository.TaskRepository;

import javax.servlet.RequestDispatcher;
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
        req.setAttribute("t", taskAll.get(0));

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/task-list.jsp");
        requestDispatcher.forward(req, resp);


    }

}
