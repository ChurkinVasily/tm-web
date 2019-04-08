package ru.churkin.api;

import ru.churkin.entity.Project;

import java.util.List;

public interface IProjectService {

    boolean createProject(String name);

    Project findProjectById(String id);

    boolean updateProject(Project project);

    boolean deleteProject(String id);

    List<Project> getProjectAll();

    Project findProjectByName(String projectName);
}
