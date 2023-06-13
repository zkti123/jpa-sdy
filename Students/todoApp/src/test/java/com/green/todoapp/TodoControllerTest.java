package com.green.todoapp;

import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoSelDto;
import com.green.todoapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mvc; //테스트를 할수 있게 요청 보낼때 사용.

    @MockBean //가짜 업무를 준다.
    private TodoService service;


    @Test
    @DisplayName("TODO - 등록")
    void postTodo() throws Exception {
        //given - when - then 환경설정 -데이터실행 -검증

        //given 테스트 세팅 <= 테스트에서 구체화하고자 하는 행동을 시작하기 전에 테스트 상태를 설명하는 부분
        //가짜 서비스한에 가짜 업무를 주기위해 사용.
        given(service.instodo(any(TodoInsDto.class))).willReturn(3);

        //when 실제 실행 <= 테스트하기위해 조건 설정
        String json = "{\"ctnt\": \"빨래 개기\" }";


        ResultActions ra = mvc.perform(post("/api/todo")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));

        // then - 검증
        ra.andExpect(status().isOk())
                .andExpect(content().string("3"))
                .andDo(print());
        verify(service).instodo(any()); // verify <= 실행됐는지 확인하는 역할

    }

    @Test
    @DisplayName("TODO - 리스트")
    void getTodo() throws Exception {
        //given - when - then
        List<TodoVo> mockList = new ArrayList();
        mockList.add(new TodoVo(1, "테스트", "2023", null,1,"2023-06-13"));
        mockList.add(new TodoVo(2, "테스트2", "2022", "abc.jpg",0,null));

        given(service.seltodo()).willReturn(mockList);
        //when
        ResultActions ra = mvc.perform(get("/api/todo"));

        //then
        ra.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(mockList.size())))
                .andExpect(jsonPath("$[*].itodo").exists()) // <== 모든 배열에 itodo 가 있는지 확인
                .andExpect(jsonPath("$[0].itodo").value(1))
                .andExpect(jsonPath("$[0].ctnt").value("테스트"))
                .andDo(print());

        verify(service).seltodo();
    }


}





