package com.green.todoapp;

import com.google.gson.Gson;
import com.green.todoapp.model.*;
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
import static org.mockito.ArgumentMatchers.anyInt;
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
        TodoInsDto dto = new TodoInsDto();
        dto.setCtnt("빨래 개기");

        Gson gson = new Gson();
        String json = gson.toJson(dto);
        //String json = "{\"ctnt\": \"빨래 개기\" }";


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
        mockList.add(new TodoVo(1, "테스트", "2023", null, 1, "2023-06-13"));
        mockList.add(new TodoVo(2, "테스트2", "2022", "abc.jpg", 0, null));

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

    @Test
    @DisplayName("TODO - 완료처리 토글")
    void patchTodo() throws Exception {
        given(service.upTodo(any())).willReturn(1);

        Gson gson = new Gson();

        TodoPatchDto dto = new TodoPatchDto();
        dto.setItodo(1);

        String json = gson.toJson(dto);

        ResultActions ra = mvc.perform(patch("/api/todo").content(json).contentType(MediaType.APPLICATION_JSON));


                 ra.andExpect(status().isOk())
                .andExpect(content().string("1"))
                .andDo(print());

        verify(service).upTodo(any());

    }

    @Test
    @DisplayName("TODO - 삭제")
    void delTodo() throws Exception {
        int itodo = 10;
        given(service.delTodo(anyInt())).willReturn(itodo);

        ResultActions ra = mvc.perform(delete("/api/todo").param("itodo",String.valueOf(itodo)));

        ra.andExpect(status().isOk()).andExpect(content().string(String.valueOf(itodo))).andDo(print());

        verify(service).delTodo(anyInt());


    }

}





