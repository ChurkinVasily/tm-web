package ru.churkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.churkin.api.*;
import ru.churkin.entity.Project;
import ru.churkin.entity.Task;
import ru.churkin.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    ITaskService taskService;

    @Autowired
    IProjectService projectService;

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String allTasks(HttpServletRequest req, Model model) {

        HttpSession session = req.getSession();
        final String userId = (String) session.getAttribute("userId");
        final String userName = (String) session.getAttribute("userName");

        if (userId == null || userId.isEmpty()) {
            return "redirect:" + "login";
        }
        List<Task> taskAll = taskService.findTaskByUserId(userId);
        model.addAttribute("allTasks", taskAll);
        model.addAttribute("currentUserName", userName);
        return "task-list";
    }

    @RequestMapping(value = "/create-task", method = RequestMethod.GET)
    public String createTask(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        String name = req.getParameter("taskName");
        User user = userService.findUserById(userId);
        taskService.createTask(name, user);
        return "redirect:" + "tasks";
    }

    @RequestMapping(value = "/task-edit", method = RequestMethod.GET)
    public String editTask(HttpServletRequest req, Model model) {
        String taskId = req.getParameter("id");
        Task task = taskService.findTaskById(taskId);
        model.addAttribute("task", task);

        Project currentProject = task.getProject();
        model.addAttribute("currentProject", currentProject);

        List<Project> allProjects = projectService.getProjectAll();
        model.addAttribute("allProjects", allProjects);

        return "task-edit";
    }

    @RequestMapping(value = "/tasks-for-project", method = RequestMethod.GET)
    public String getTaskbyProject(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        String projectId = req.getParameter("id");
        List<Task> tasksByProjectId = taskService.getByProjectId(projectId, userId);
        model.addAttribute("allTasks", tasksByProjectId);
        return "task-list";
    }

    @RequestMapping(value = "/task-remove", method = RequestMethod.GET)
    public String removeTask(HttpServletRequest req, Model model) {
        final String taskId = req.getParameter("id");
        taskService.deleteTask(taskId);
        return "redirect:" + "tasks";
    }

    @RequestMapping(value = "/task-save", method = RequestMethod.POST)
    public String saveTask(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        User user = userService.findUserById(userId);

        String id = req.getParameter("taskId");
        Task task = taskService.findTaskById(id);
        task.setName(req.getParameter("taskName"));
        task.setDescription(req.getParameter("taskDescription"));
        final String projectName = req.getParameter("projectName");
        Project project = projectService.findProjectByName(projectName);
        String projectId = project.getId();

        task.setProject(project);
        task.setUser(user);

        taskService.updateTask(task);
        return "redirect:" + "tasks-for-project?id=" +projectId;
    }
}
