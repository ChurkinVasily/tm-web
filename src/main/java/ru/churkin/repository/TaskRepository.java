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
        task1.setProjectId("111");
        task1.setUserId("u1");
        Task task2 = new Task("task2(p3)");
        task2.setProjectId("333");
        task2.setUserId("u2");
        Task task3 = new Task("task3(p2)");
        task3.setProjectId("222");
        task3.setUserId("u2");
        Task task4 = new Task("task4(p2)");
        task4.setProjectId("222");
        task4.setUserId("u1");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
    }

    @Override
    public void createTask(final String name, final @Nullable String userId) {
        if (userId == null || userId.isEmpty()) return;
        Task task = new Task(name);
        task.setUserId(userId);
        taskList.add(task);
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
    public List<Task> getByProjectId(final @Nullable String projectId, final @Nullable String userId) {
        if (projectId == null || projectId.isEmpty()) return null;
        if (userId == null || userId.isEmpty()) return null;
        List<Task> list = new ArrayList<>();
        for (Task itask : taskList) {
            if (projectId.equals(itask.getProjectId()) && userId.equals(itask.getUserId())) {
                list.add(itask);
            }
        }
        return list;
    }

    @Override
    public List<Task> getByUserId(final @Nullable String userId) {
        if (userId == null || userId.isEmpty()) return null;
        List<Task> list = new ArrayList<>();
        for (Task itask : taskList) {
            if (userId.equals(itask.getUserId())) {
                list.add(itask);
            }
        }
        return list;
    }

    @Override
    public void updateTask(Task task) {
        String id = task.getId();
        for (Task tsk : taskList) {
            if (tsk.getId().equals(id)) {
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
