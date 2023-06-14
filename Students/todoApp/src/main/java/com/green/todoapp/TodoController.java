package com.green.todoapp;

import com.green.todoapp.model.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService service;
    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }


    @PostMapping
    public int postTodo(@RequestBody TodoInsDto dto) {
        return service.instodo(dto);
    }

    @GetMapping
    public List<TodoVo> getTodo() {

        return service.seltodo();
    }

    @PatchMapping
    @Operation(summary = "피니쉬")
    public int patchTodo(@RequestBody TodoPatchDto dto) {

        return service.upTodo(dto);
    }

    @DeleteMapping
    @Operation(summary = "삭제")
    public int delTodo(@RequestParam int itodo){

        return service.delTodo(itodo);
    }
}
