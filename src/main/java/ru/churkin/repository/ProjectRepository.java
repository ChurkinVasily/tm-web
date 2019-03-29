package ru.churkin.repository;

import ru.churkin.api.IProjectRepository;
import ru.churkin.entity.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements IProjectRepository {

    private static List<Project> projectList = new ArrayList<>();

    static {
        Project project1 = new Project("Project1");
        project1.setId("111");
        Project project2 = new Project("Project2");
        project2.setId("222");
        Project project3 = new Project("Project3");
        project3.setId("333");

        projectList.add(project1);
        projectList.add(project2);
        projectList.add(project3);

    }

    @Override
    public void createProject(String name) {
        projectList.add(new Project(name));
    }

    @Override
    public List<Project> getProjectAll() {
        return projectList;
    }

    @Override
    public Project findProjectById(String id) {
        if (id.isEmpty()) return null;
        Project project = null;
        for (Project iproject : projectList) {
            if (id.equals(iproject.getId())) {
                project = iproject;
                break;
            }
        }
        return project;
    }

    @Override
    public void updateProject(Project project) {
        String id = project.getId();
        for (Project proj : projectList) {
            if (proj.getId().equals(id)) {
                projectList.remove(proj);
                projectList.add(project);
                return;
            }
        }
    }

    @Override
    public void deleteProjectById(String id) {
        if (id.isEmpty()) return;
        for (Project project : projectList) {
            if (id.equals(project.getId())) {
                projectList.remove(project);
                return;
            }
        }
    }

    @Override
    public Project findProjectByName(String name) {
        if (name.isEmpty()) return null;
        Project project = null;
        for (Project iproject : projectList) {
            if (name.equals(iproject.getName())) {
                project = iproject;
                break;
            }
        }
        return project;
    }
}
