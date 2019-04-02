package ru.churkin.controller.task;

import ru.churkin.api.ITaskRepository;
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

@WebServlet(urlPatterns = "/tasks")
public class TaskAllServlet extends HttpServlet {

    private ITaskRepository taskRepository;

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        final String userId = (String) session.getAttribute("userId");
        final String userName = (String) session.getAttribute("userName");


        if (userId == null || userId.isEmpty()) {
            resp.sendRedirect("login");
            return;
        }
        List<Task> taskAll = taskRepository.getByUserId(userId);
        req.setAttribute("allTasks", taskAll);
        req.setAttribute("currentUserName", userName);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/task-list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
