package com.green.boardtest.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "회원가입")
public class UserEntity {
    public int iuser;
    @Schema(description = "[30] 회원 아이디")
    public String uid;
    @Schema(description = "[100] 회원 비밀번호")
    public String upw;
    public String nm;
    public char gender;
    public String addr;
    public String mainPic;
    public String createdAt;
    public String updatedAt;
}
