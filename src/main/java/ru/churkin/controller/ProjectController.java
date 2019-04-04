package ru.churkin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.churkin.api.IProjectRepository;
import ru.churkin.entity.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    IProjectRepository projectRepository;

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String allProjects(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        final String userId = (String) session.getAttribute("userId");
        final String userName = (String) session.getAttribute("userName");

        if (userId == null || userId.isEmpty()) {
            return "redirect:" + "login";
        }

        List<Project> projectsAll = projectRepository.getProjectAll();
        model.addAttribute("allProjects", projectsAll);
        model.addAttribute("currentUserName", userName);
        return "project-list";
    }

    @RequestMapping(value = "/create-project", method = RequestMethod.GET)
    public String crateProject(HttpServletRequest req, Model model) {

        String name = req.getParameter("projectName");
        projectRepository.createProject(name);
        return "redirect:" + "projects";
    }


    @RequestMapping(value = "/project-edit", method = RequestMethod.GET)
    public String editProject(HttpServletRequest req, Model model) {

        String projectId = req.getParameter("id");
        Project project = projectRepository.findProjectById(projectId);

        model.addAttribute("project", project);
        return "project-edit";
    }

    @RequestMapping(value = "/project-remove", method = RequestMethod.GET)
    public String removeProject(HttpServletRequest req, Model model) {
        final String projectId = req.getParameter("id");
        projectRepository.deleteProjectById(projectId);
        return "redirect:" + "projects";
    }

    @RequestMapping(value = "/project-save", method = RequestMethod.POST)
    public String saveProject(HttpServletRequest req, Model model) {
        String id = req.getParameter("projectId");
        Project project = projectRepository.findProjectById(id);

        project.setName(req.getParameter("projectName"));
        project.setDescription(req.getParameter("projectDescription"));
        project.setTimeStart(req.getParameter("projectDateStart"));
        project.setTimeFinish(req.getParameter("projectDateFin"));

        projectRepository.updateProject(project);

        return "redirect:" + "projects";
    }

}
