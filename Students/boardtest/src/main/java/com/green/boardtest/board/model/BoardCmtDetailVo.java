package com.green.boardtest.board.model;

import com.green.boardtest.cmt.model.CmtRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardCmtDetailVo {
    private int iboard;
    private String title;
    private String ctnt;
    private String createdAt;
    private String writer;
    private String writerMainPic;
    private CmtRes cmt;
}
