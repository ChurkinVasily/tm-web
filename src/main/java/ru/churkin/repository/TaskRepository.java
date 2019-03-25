package ru.churkin.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.churkin.api.ITaskRepository;
import ru.churkin.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements ITaskRepository {

    private static List<Task> taskList = new ArrayList<>();

    static {
        Task task1 = new Task("task1");
        Task task2 = new Task("task2");
        Task task3 = new Task("task3");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
    }

    @Override
    public void createTask(String name) {
        Task task = new Task(name);
        taskList.add(task);
    }

    @Nullable
    @Override
    public Task findTaskByName(@NotNull String name) {
        Task task = null;
        if (name.isEmpty()) return null;
        for (Task itask : taskList) {
            if (itask.getName().equals(name)) task = itask;
            break;
        }
        return task;
    }

    @Override
    public List<Task> getTaskAll() {
        return taskList;
    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void deleteTaskByName(String name) {

    }

    @Override
    public void deleteTaskById(String id) {

    }
}
