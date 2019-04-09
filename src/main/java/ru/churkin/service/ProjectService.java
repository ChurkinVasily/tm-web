package ru.churkin.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.churkin.api.IProjectService;
import ru.churkin.entity.Project;
import ru.churkin.repository.ProjectRepositoryJPA;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@NoArgsConstructor
public class ProjectService implements IProjectService {

    @Autowired
    ProjectRepositoryJPA projectRepository;

    @Override
    public List<Project> getProjectAll() {
        List<Project> listProject = projectRepository.findAll();
        if (listProject.isEmpty()) return null;
        return listProject;
    }

    @Override
    public boolean createProject(String projectName) {
        boolean isConsist = false;
        for (Project cProject : projectRepository.findAll()) {
            if (projectName.equals(cProject.getName())) {
                isConsist = true;
            }
        }
        if (isConsist) {
            return false;
        }
        Project project = new Project(projectName);
        projectRepository.save(project);
        return true;
    }

    @Override
    public boolean deleteProject(String id) {
        Optional<Project> projectList = projectRepository.findById(id);
        Project project = (Project) projectList.get();
        projectRepository.delete(project);
        return true;
    }

    @Override
    public Project findProjectById(@Nullable String id) {
        if (id.isEmpty() || id == null) return null;
        Optional<Project> projectList = projectRepository.findById(id);
        Project project = (Project) projectList.get();
        return project;
    }


    @Override
    public Project findProjectByName(@Nullable String projectName) {
        if (projectName == null) return null;
        return projectRepository.findProjectByName(projectName);
    }

    @Override
    public boolean updateProject(@Nullable Project project) {
        if (project == null) return false;
        projectRepository.save(project);
        return true;
    }
}
