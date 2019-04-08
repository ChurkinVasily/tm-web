package ru.churkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.churkin.api.IProjectService;
import ru.churkin.entity.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    IProjectService projectService;

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String allProjects(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        final String userId = (String) session.getAttribute("userId");
        final String userName = (String) session.getAttribute("userName");

        if (userId == null || userId.isEmpty()) {
            return "redirect:" + "login";
        }

        List<Project> projectsAll = projectService.getProjectAll();
        model.addAttribute("allProjects", projectsAll);
        model.addAttribute("currentUserName", userName);
        return "project-list";
    }

    @RequestMapping(value = "/create-project", method = RequestMethod.GET)
    public String crateProject(HttpServletRequest req, Model model) {
        String name = req.getParameter("projectName");
        projectService.createProject(name);
        return "redirect:" + "projects";
    }

    @RequestMapping(value = "/project-edit", method = RequestMethod.GET)
    public String editProject(HttpServletRequest req, Model model) {

        String projectId = req.getParameter("id");
        Project project = projectService.findProjectById(projectId);

        model.addAttribute("project", project);
        return "project-edit";
    }

    @RequestMapping(value = "/project-remove", method = RequestMethod.GET)
    public String removeProject(HttpServletRequest req, Model model) {
        final String projectId = req.getParameter("id");
        projectService.deleteProject(projectId);
        return "redirect:" + "projects";
    }

    @RequestMapping(value = "/project-save", method = RequestMethod.POST)
    public String saveProject(HttpServletRequest req, Model model) {
        String id = req.getParameter("projectId");
        Project project = projectService.findProjectById(id);

        project.setName(req.getParameter("projectName"));
        project.setDescription(req.getParameter("projectDescription"));
        project.setTimeStart(req.getParameter("projectDateStart"));
        project.setTimeFinish(req.getParameter("projectDateFin"));

        projectService.updateProject(project);

        return "redirect:" + "projects";
    }
}
