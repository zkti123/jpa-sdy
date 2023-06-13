package com.green.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public class TodoVo {
    private int itodo;
    private String ctnt;
    private String createdAt;
    private String pic;
    private int finishYn;
    private String finishedAt;
}
