package ru.churkin.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class Task {

    private String id;
    private String name;
    private String description;
    private String projectId;
    private String userId;

    public Task(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
