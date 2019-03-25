package ru.churkin.api;

import ru.churkin.entity.Project;

import java.util.List;

public interface IProjectRepository {

    void createProject(String name);

    Project findProjectByName(String name);

    List<Project> getProjectAll();

    void updateProject(Project Project);

    void deleteProjectByName(String name);

    void deleteProjectById(String id);
}
