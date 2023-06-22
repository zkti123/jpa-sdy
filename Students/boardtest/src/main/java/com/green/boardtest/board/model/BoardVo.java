package com.green.boardtest.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardVo {
    private int iboard;
    private String title;
    private String createdAt;
    private String writer;
    private String writerMainPic;
}
