package ru.churkin.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {

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

    @Nullable
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Project(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeFinish='" + timeFinish + '\'' +
                '}';
    }
}
