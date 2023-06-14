package com.green.todoapp;

import com.green.todoapp.model.*;
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
import static org.mockito.BDDMockito.given;
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
        vo.add(new TodoVo(1, "테스트", "2023", null,1,"2023-06-13"));


        //when
        when(mapper.seltodo()).thenReturn(vo);

        List<TodoVo> result = service.seltodo();

        assertEquals(vo,result.size());


        //then
        verify(mapper).seltodo();

    }

    @Test
    @DisplayName("TodoService - todo 완료처리 토글")
    void upTodo() {
        //given
        TodoPatchDto dto = new TodoPatchDto();
        dto.setItodo(1);



        //when
        when(mapper.upTodo(any())).thenReturn(1);

        int result = service.upTodo(dto);

        //then
        assertEquals(0,result);

        verify(mapper).upTodo(any());

    }

    @Test
    @DisplayName("TodoService - todo 삭제")
    void delTodo() {
       int expectedResult = 1;

        when(mapper.delTodo(any(TodoEntity.class))).thenReturn(expectedResult);

        int result = service.delTodo(anyInt());

        assertEquals(expectedResult,result);

        verify(mapper).delTodo(any(TodoEntity.class));


    }





}