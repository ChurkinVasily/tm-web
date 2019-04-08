package ru.churkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.churkin.api.IProjectRepository;
import ru.churkin.api.ITaskRepository;
import ru.churkin.api.IUserRepository;
import ru.churkin.entity.Project;
import ru.churkin.entity.Task;
import ru.churkin.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    ITaskRepository taskRepository;

    @Autowired
    IProjectRepository projectRepository;

    @Autowired
    IUserRepository userRepository;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String allTasks(HttpServletRequest req, Model model) {

        HttpSession session = req.getSession();
        final String userId = (String) session.getAttribute("userId");
        final String userName = (String) session.getAttribute("userName");

        if (userId == null || userId.isEmpty()) {
            return "redirect:" + "login";
        }
//        List<Task> taskAll = taskRepository.getByUserId(userId);
        List<Task> taskAll = taskRepository.getTaskAll();
        model.addAttribute("allTasks", taskAll);
        model.addAttribute("currentUserName", userName);
        return "task-list";
    }

    @RequestMapping(value = "/create-task", method = RequestMethod.GET)
    public String createTask(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        String name = req.getParameter("taskName");
        taskRepository.createTask(name, userId);
        return "redirect:" + "tasks";
    }

    @RequestMapping(value = "/task-edit", method = RequestMethod.GET)
    public String editTask(HttpServletRequest req, Model model) {
        String taskId = req.getParameter("id");
        Task task = taskRepository.findTaskById(taskId);
        model.addAttribute("task", task);

        Project currentProject = projectRepository.findProjectById(task.getProject().getId());
        model.addAttribute("currentProject", currentProject);

        List<Project> allProjects = projectRepository.getProjectAll();
        model.addAttribute("allProjects", allProjects);

        return "task-edit";
    }

    @RequestMapping(value = "/tasks-for-project", method = RequestMethod.GET)
    public String getTaskbyProject(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        String projectId = req.getParameter("id");
//        List<Task> tasksByProjectId = taskRepository.getByProjectId(projectId, userId);
        List<Task> tasksByProjectId = taskRepository.getTaskAll();
        model.addAttribute("allTasks", tasksByProjectId);
        return "task-list";
    }

    @RequestMapping(value = "/task-remove", method = RequestMethod.GET)
    public String removeTask(HttpServletRequest req, Model model) {
        final String taskId = req.getParameter("id");
//        taskRepository.deleteTaskById(taskId);
        Task task = taskRepository.findTaskById(taskId); /// ----- новая строка
        taskRepository.deleteTask(task);
        return "redirect:" + "tasks";
    }

    @RequestMapping(value = "/task-save", method = RequestMethod.POST)
    public String saveTask(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");
        User user = userRepository.findUserById(userId);

        String id = req.getParameter("taskId");
        Task task = taskRepository.findTaskById(id);
        task.setName(req.getParameter("taskName"));
        task.setDescription(req.getParameter("taskDescription"));
        final String projectName = req.getParameter("projectName");
        Project project = projectRepository.findProjectByName(projectName);
        String projectId = project.getId();

//        task.setProjectId(projectId);
//        task.setUserId(userId);

        task.setProject(project);
        task.setUser(user);

        taskRepository.updateTask(task);
        return "redirect:" + "tasks-for-project?id=" +projectId;
    }
}
