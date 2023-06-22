package com.green.boardtest.board.model;

import com.green.boardtest.cmt.model.CmtRes;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardCmtDetailVo2 {
    private BoardDetailVo board;
    private CmtRes cmt;
}
