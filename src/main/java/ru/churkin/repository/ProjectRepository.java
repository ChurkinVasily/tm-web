package ru.churkin.repository;

import ru.churkin.api.IProjectRepository;
import ru.churkin.entity.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements IProjectRepository {

    private static List<Project> projectList = new ArrayList<>();

    static {
      Project Project1 = new Project("Project1");
      Project Project2 = new Project("Project2");
      Project Project3 = new Project("Project3");
      
      projectList.add(Project1);
      projectList.add(Project2);
      projectList.add(Project3);
      
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
    public void updateProject(Project Project) {

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
}
