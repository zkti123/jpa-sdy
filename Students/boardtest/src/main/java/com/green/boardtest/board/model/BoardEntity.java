package com.green.boardtest.board.model;

import lombok.Data;

@Data
public class BoardEntity {
    private int iboard;
    private String title;
    private String ctnt;
    private int iuser;
    private String createdAt;
    private String updatedAt;

}
