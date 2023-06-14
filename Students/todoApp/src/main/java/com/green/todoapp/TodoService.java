package com.green.todoapp;

import com.green.todoapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoMapper mapper;

    @Autowired
    public TodoService(TodoMapper mapper) {
        this.mapper = mapper;
    }

    public int instodo(TodoInsDto dto) {
        TodoEntity entity = new TodoEntity();

        entity.setCtnt(dto.getCtnt());
        int result = mapper.instodo(entity);
        if (result == 1) {
            return entity.getItodo();
        }

        return result;
    }
    public List<TodoVo> seltodo() {

        return mapper.seltodo();
    }

    public int upTodo(TodoPatchDto dto){
        TodoEntity entity = new TodoEntity();
        entity.setItodo(dto.getItodo());

        int result = mapper.upTodo(entity);
        System.out.println(entity.getFinishYn());
        return entity.getFinishYn();
    }

    public int delTodo(int itodo){
        TodoEntity entity = new TodoEntity();
        entity.setItodo(itodo);

        return mapper.delTodo(entity);

    }


}
