package ru.churkin.api;

import ru.churkin.entity.Project;

import java.util.List;

public interface IProjectRepository {

    void createProject(String name);

    List<Project> getProjectAll();

    void updateProject(Project Project);

    void deleteProjectById(String id);

    Project findProjectById(String id);


}
