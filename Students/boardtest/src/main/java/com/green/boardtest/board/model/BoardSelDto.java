package com.green.boardtest.board.model;

import lombok.Data;

@Data
public class BoardSelDto {
    private int iboard;
    private int startIdx;
    private int page;
    private int row;
}
