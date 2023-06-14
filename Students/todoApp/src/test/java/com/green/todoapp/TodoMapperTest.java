package com.green.todoapp;

import com.green.todoapp.model.TodoDelDto;
import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoMapperTest {

    @Autowired
    private TodoMapper mapper;

    @Test
    void instodo() {
        //given
        TodoEntity entity = new TodoEntity();
        entity.setCtnt("테스트");

        int result = mapper.instodo(entity);
        System.out.println(entity.getItodo());

        assertEquals(6,entity.getItodo());
        assertEquals(1,result);

    }

    @Test
    void seltodo() {
        List<TodoVo> list = mapper.seltodo();

        assertEquals(5,list.size());

        TodoVo vo = list.get(0);
        assertEquals(1,vo.getItodo());
        assertEquals("ddd",vo.getCtnt());
    }

    @Test
    void upTodo() {
        TodoEntity entity = new TodoEntity();
        entity.setItodo(1);

        int result = mapper.upTodo(entity);

        assertEquals(1,result);
    }

    @Test
    @DisplayName("todoMapper - Todo 삭제")
    void delTodo() {
        int expectedResult = 1;
        TodoEntity entity = new TodoEntity();
        entity.setItodo(1);

        int result = mapper.delTodo(entity);

        assertEquals(expectedResult,result);
    }

}