package com.green.boardtest.user;

import com.green.boardtest.user.model.*;
import com.green.boardtest.utis.CommonUtils;
import com.green.boardtest.utis.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UserService {
    private final UserMapper mapper;
    private final CommonUtils commonUtils;
    @Value("${file.dir}")
    private String fileDir;

    @Autowired
    public UserService(UserMapper mapper, CommonUtils commonUtils) {
        this.mapper = mapper;
        this.commonUtils = commonUtils;
    }

    public int insUser(UserInsDto dto){
        //성별 항상 대문자로 변경
        char gender = Character.toUpperCase(dto.getGender());
        if (!(gender == 'M' || gender == 'F')){
            return -1;
        }
        dto.setGender(gender);

        //비밀번호 암호화
        String hashedPw = commonUtils.encodeSha256(dto.getUpw());
        dto.setUpw(hashedPw);
        try {
            return mapper.insUser(dto);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public int login(UserLoginDto dto){
        UserLoginVo vo = mapper.selUserByUid(dto);
        if(vo == null){return 2;}
        String hashedPw = commonUtils.encodeSha256(dto.getUpw());
        if (vo.getUpw().equals(hashedPw)){
            return 1;
        }
        return 3;
    }
    public int updUserPw(UserPatchPwDto dto){
        String hashedPw = commonUtils.encodeSha256(dto.getUpw());
        dto.setUpw(hashedPw);
        return mapper.updUserPw(dto);
    }
    public int updUserPic(MultipartFile pic, UserPatchPicDto dto){
    String centerPath = String.format("user/%d", dto.getIuser());
        String dicPath = String.format("%s/%s", fileDir, centerPath);


        File dic = new File(dicPath); // 폴더 생성 여부 확인 및 생성, 삭제 수행
        if (!dic.exists()){//폴더가 존재하지 않는 경우
            dic.mkdirs(); //생성 수행
        }
        String originFileName = pic.getOriginalFilename();
        String savedFileName = FileUtils.makeRandomFileNm(originFileName);
        String savedFilePath = String.format("%s/%s", centerPath, savedFileName);
        String targetPath    = String.format("%s/%s", fileDir, savedFileName);
        File target = new File(targetPath);
        try {
            pic.transferTo(target);

        }catch (Exception e){
            return 0;
        }
        dto.setMainPic((savedFilePath));
        try {
            int result = mapper.updUserPic(dto);
            if (result == 0){
                throw new Exception("프로필 사진을 등록할 수 없습니다.");
            }
        }catch (Exception e){
            //파일 삭제
            target.delete();
            return 0;
        }
        return 1;
        }




}
