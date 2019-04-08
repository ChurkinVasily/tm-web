package ru.churkin.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.churkin.api.IProjectRepository;
import ru.churkin.entity.Project;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository implements IProjectRepository {

    @Autowired
    EntityManager entityManager;

    private List<Project> projectList = new ArrayList<>();

//    @PostConstruct
//    public void init() {
//        Project project1 = new Project("Project1");
//        project1.setId("111");
//        Project project2 = new Project("Project2");
//        project2.setId("222");
//        Project project3 = new Project("Project3");
//        project3.setId("333");
//        Project project4 = new Project("Project4");
//        projectList.add(project1);
//        projectList.add(project2);
//        projectList.add(project3);
//        projectList.add(project4);
//    }

//    @Override
//    public void createProject(String name) {
//        projectList.add(new Project(name));
//    }

    @Override
    public void createProject(Project project) {
        entityManager.persist(project);
    }


//    @Override
//    public List<Project> getProjectAll() {
//        return projectList;
//    }


    @Override
    public List<Project> getProjectAll() {
        return entityManager.createQuery("select e from Project e", Project.class)
                .getResultList();
    }

//    @Override
//    public Project findProjectById(String id) {
//        if (id == null || id.isEmpty()) return null;
//        Project project = null;
//        for (Project iproject : projectList) {
//            if (id.equals(iproject.getId())) {
//                project = iproject;
//                break;
//            }
//        }
//        return project;
//    }


    @Override
    public Project findProjectById(String id) {
        return entityManager.find(Project.class, id);
    }

//    @Override
//    public void updateProject(Project project) {
//        String id = project.getId();
//        for (Project proj : projectList) {
//            if (id.equals(proj.getId())) {
//                projectList.remove(proj);
//                projectList.add(project);
//                return;
//            }
//        }
//    }

    @Override
    public void updateProject(Project project) {
        entityManager.merge(project);
    }


//    @Override
//    public void deleteProjectById(String id) {
//        if (id.isEmpty()) return;
//        for (Project project : projectList) {
//            if (id.equals(project.getId())) {
//                projectList.remove(project);
//                return;
//            }
//        }
//    }

    @Override
    public void deleteProject(Project project) {
        entityManager.remove(project);
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
