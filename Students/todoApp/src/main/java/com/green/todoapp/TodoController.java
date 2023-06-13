package com.green.todoapp;

import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoPatchDto;
import com.green.todoapp.model.TodoSelDto;
import com.green.todoapp.model.TodoVo;
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

    @PatchMapping("{itodo}")
    public int patchTodo(@PathVariable int itodo,@RequestParam int finishYn) {
        TodoPatchDto dto = new TodoPatchDto();
        dto.setFinishYn(finishYn);
        dto.setItodo(itodo);
        return service.upTodo(dto);
    }
}
