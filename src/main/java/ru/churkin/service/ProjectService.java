package ru.churkin.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.churkin.api.IProjectRepository;
import ru.churkin.api.IProjectService;
import ru.churkin.entity.Project;

import java.util.List;

@Service
@Transactional
@NoArgsConstructor
public class ProjectService implements IProjectService {

    @Autowired
    IProjectRepository projectRepository;

    @Override
    public List<Project> getProjectAll() {
        List<Project> listProject = projectRepository.getProjectAll();
        if (listProject.isEmpty()) return null;
        return listProject;
    }

    @Override
    public boolean createProject(String projectName) {
        boolean isConsist = false;
        for (Project cProject : projectRepository.getProjectAll()) {
            if (projectName.equals(cProject.getName())) {
                isConsist = true;
            }
        }
        if (isConsist) {
            return false;
        }
        Project project = new Project(projectName);
        projectRepository.createProject(project);
        return true;
    }

    @Override
    public boolean deleteProject(String id) {
        Project project = projectRepository.findProjectById(id);
        projectRepository.deleteProject(project);
        return true;
    }

    @Override
    public Project findProjectById(@Nullable String id) {
        if (id.isEmpty() || id == null) return null;
        return projectRepository.findProjectById(id);
    }

    @Override
    public boolean updateProject(@Nullable Project project) {
        if (project == null) return false;
        projectRepository.updateProject(project);
        return true;
    }
}
