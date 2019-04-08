package ru.churkin.api;

import ru.churkin.entity.Project;

import java.util.List;

public interface IProjectRepository {

    void createProject(Project project);

    List<Project> getProjectAll();

    void updateProject(Project project);

    void deleteProject(Project project);

    Project findProjectById(String id);

    Project findProjectByName(String name);


}
