package com.green.boardtest.user;

import com.green.boardtest.user.model.UserInsDto;
import com.green.boardtest.user.model.UserLoginDto;
import com.green.boardtest.user.model.UserPatchPicDto;
import com.green.boardtest.user.model.UserPatchPwDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequestMapping("/user")
@Tag(name = "유저", description = "")
public class UserController {
    private final UserService service;


    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "회원가입", description = "" +
            "uid: [20] 회원 아이디,<br>" +
            "upw: [100] 회원 비밀번호,<br>" +
            "nm: [30] 회원명,<br>" +
            "gender: [1] 성별(M: 남성, F: 여성),<br>" +
            "addr: [100] 대구시 달서구")
    public int postUser(@RequestBody UserInsDto dto) {
        return service.insUser(dto);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "" +
            "리턴값 : " + "(1)로그인 성공" + "(2)아이디 없음" + "(3)비밀번호 다름")
    public int posLoginUser(@RequestBody UserLoginDto dto) {
        return service.login(dto);
    }

    @PatchMapping("/pw")
    @Operation(summary = "비밀번호 수정", description = "")
    public int pathPwUser(@RequestBody UserPatchPwDto dto) {
        return service.updUserPw(dto);
    }

    @PatchMapping(name = "/pic", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "파일 업로드", description = "")
    public int patchPicUser(@RequestPart MultipartFile pic, @RequestParam int iuser) {
        UserPatchPicDto dto = new UserPatchPicDto();
        dto.setIuser(iuser);
        return service.updUserPic(pic, dto);
    }
}
