package ru.churkin.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.churkin.api.ITaskRepository;
import ru.churkin.entity.Task;
import ru.churkin.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TaskRepository implements ITaskRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public void createTask(String name, User user) {
        Task task = new Task(name);
        task.setUser(user);
        entityManager.persist(task);
    }

    @Override
    public Task findTaskById(String id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    public List<Task> getTaskAll() {
        return entityManager.createQuery("select e from Task e", Task.class).getResultList();
    }

    @Override
    public List<Task> getByProjectId(String projectId, String userId) {
        return entityManager.createQuery("select t from Task t where t.user.id = :userId and t.project.id = :projectId", Task.class)
                .setParameter("userId", userId)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    @Override
    public List<Task> getByUserId(String userId) {
        return entityManager.createQuery("select t from Task t where t.user.id = :userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void updateTask(Task task) {
        entityManager.merge(task);
    }

    @Override
    public void deleteTask(Task task) {
        entityManager.remove(task);
    }
}
