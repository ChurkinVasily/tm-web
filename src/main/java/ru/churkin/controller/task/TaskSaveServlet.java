package ru.churkin.controller.task;

import ru.churkin.entity.Task;
import ru.churkin.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/task-save")
public class TaskSaveServlet extends HttpServlet {

    Logger log = Logger.getLogger(this.getClass().getName());


    private TaskRepository taskRepository;

    @Override
    public void init() throws ServletException {
        taskRepository = new TaskRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("===========task-save doPost");

        String id = req.getParameter("taskId");

        log.info("======task id : " + id);

        Task task = taskRepository.findTaskById(id);

        log.info("======task initialize" + task);

        task.setName(req.getParameter("taskName"));
        task.setDescription(req.getParameter("taskDescription"));

        taskRepository.updateTask(task);
        resp.sendRedirect("tasks");
    }
}
