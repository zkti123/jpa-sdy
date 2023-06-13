package com.green.todoapp;

import com.green.todoapp.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    int instodo(TodoEntity entity);

    List<TodoVo> seltodo();

    int upTodo(TodoPatchDto dto);
}
