package ru.churkin.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.churkin.api.IProjectRepository;
import ru.churkin.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProjectRepository implements IProjectRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public void createProject(Project project) {
        entityManager.persist(project);
    }

    @Override
    public List<Project> getProjectAll() {
        return entityManager.createQuery("select e from Project e", Project.class)
                .getResultList();
    }


    @Override
    public Project findProjectById(String id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public void updateProject(Project project) {
        entityManager.merge(project);
    }

    @Override
    public void deleteProject(Project project) {
        entityManager.remove(project);
    }

    @Override
    public Project findProjectByName(String name) {
        return entityManager.createQuery("select p from Project p where p.name = :projectName", Project.class)
                .setParameter("projectName", name)
                .getResultList()
                .get(0);
    }
}
