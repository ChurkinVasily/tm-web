package ru.churkin.api;

import ru.churkin.entity.Project;

import java.util.List;

public interface IProjectService {

//    boolean createProject(Project project);

    boolean createProject(String name);
//
//    boolean createProject(String projectName);
//
//    Project findProjectByName(String name);
//
    Project findProjectById(String id);

    boolean updateProject(Project project);

    boolean deleteProject(String id);
//
    List<Project> getProjectAll();
}
