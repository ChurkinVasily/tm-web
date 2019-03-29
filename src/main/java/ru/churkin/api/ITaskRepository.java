package ru.churkin.api;

import ru.churkin.entity.Task;

import java.util.List;

public interface ITaskRepository {

    void createTask(String name);

    Task findTaskById(String name);

    List<Task> getTaskAll();

    List<Task> getByProjectId(String projectId);

    void updateTask(Task task);

    void deleteTaskByName(String name);

    void deleteTaskById(String id);

//    void deleteTaskByNameForUser(String name, String userId);

}
