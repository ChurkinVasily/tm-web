package ru.churkin.api;

import ru.churkin.entity.Task;
import ru.churkin.entity.User;

import java.util.List;

public interface ITaskRepository {

    void createTask(String name, User user);

    Task findTaskById(String id);

    List<Task> getTaskAll();

    List<Task> getByProjectId(String projectId, String userId);

    List<Task> getByUserId(String userId);

    void updateTask(Task task);

    void deleteTask(Task task);
}
