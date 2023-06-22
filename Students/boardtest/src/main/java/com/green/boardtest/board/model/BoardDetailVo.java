package com.green.boardtest.board.model;

import com.green.boardtest.cmt.model.CmtRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardDetailVo {
    private int iboard;
    private String title;
    private String ctnt;
    private String createdAt;
    private String writer;
    private String writerMainPic;
}
