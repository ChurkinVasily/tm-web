package ru.churkin.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    private String id;
    @Nullable
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String timeStart;
    @Nullable
    private String timeFinish;

    @ManyToOne
    @JoinColumn(name = "projectID")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    public Task(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Task(String id, User user) {
        this.id = id;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", project='" + project.getName() + '\'' +
                ", user='" + user.getName() + '\'' +
                '}';
    }


}
