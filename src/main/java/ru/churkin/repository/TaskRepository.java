package ru.churkin.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.churkin.api.ITaskRepository;
import ru.churkin.entity.Project;
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
        taskList.add(new Task(name));
    }

    @Nullable
    @Override
    public Task findTaskById(@NotNull String id) {
        if (id.isEmpty()) return null;
        Task task = null;
        for (Task itask : taskList) {
            if (id.equals(itask.getId())) {
                task = itask;
                break;
            }
        }
        return task;
    }

    @Override
    public List<Task> getTaskAll() {
        return taskList;
    }

    @Override
    public void updateTask(Task task) {
        String id = task.getId();
        for (Task tsk : taskList) {
            if (tsk.getId().equals(id)){
                taskList.remove(tsk);
                taskList.add(tsk);
                return;
            }
        }

    }

    @Override
    public void deleteTaskByName(String name) {

    }

    @Override
    public void deleteTaskById(String id) {
        if (id.isEmpty()) return;
        for (Task task : taskList) {
            if (id.equals(task.getId())) {
                taskList.remove(task);
                return;
            }
        }
    }
}
