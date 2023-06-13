package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoSelDto;
import com.green.todoapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({TodoService.class})
class TodoServiceTest {

    @MockBean
    private TodoMapper mapper;

    @Autowired
    private TodoService service;


    @Test
    @DisplayName("TodoService - todo 등록")
    void instodo() {
        when(mapper.instodo(any(TodoEntity.class))).thenReturn(1);


        TodoInsDto dto = new TodoInsDto();
        dto.setCtnt("내용 입력");
        int result = service.instodo(dto);

        assertEquals(0,result);

        verify(mapper).instodo(any(TodoEntity.class));
    }

    @Test
    @DisplayName("TodoService - todo 리스트 가져오기")
    void seltodo() {
        //given
        List<TodoVo> vo = new ArrayList<>();
        vo.add(new TodoVo(1, "테스트", "2023", null));


        //when
        when(mapper.seltodo()).thenReturn(vo);

        List<TodoVo> result = service.seltodo();

        assertEquals(vo,result.size());


        //then
        verify(mapper).seltodo();

    }
}