package com.green.boardtest.cmt.model;

import lombok.Data;

@Data
public class CmtEntity {
    private int iboardcmt;
    private int iboard;
    private int iuser;
    private String ctnt;
    private String createdAt;
    private String updatedAt;
}
