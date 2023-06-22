package com.green.boardtest.cmt;

import com.green.boardtest.cmt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmtService {
    private final CmtMapper mapper;
    @Autowired
    public CmtService(CmtMapper mapper) {
        this.mapper = mapper;
    }
    public int insBoardCmt(CmtEntity entity){
        try {
            int rusult = mapper.insBoardCmt(entity);
            if (rusult == 1)
                return entity.getIboardcmt();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public CmtRes selBoardCmt(CmtSelDto dto){
        int startIdx = (dto.getPage() - 1) * dto.getRow();
        dto.setStartIdx(startIdx);
        List<CmtVo> list = mapper.selBoardCmt(dto);

        int rowCnt = mapper.selBoardCmtRowCountByIBoard(dto.getIboard());
        int maxpage = (int)Math.ceil((double)rowCnt/dto.getRow());
        int isMore = maxpage > dto.getPage() ? 1: 0;
        return CmtRes.builder().list(list).isMore(isMore).maxPage(maxpage)
                .row(dto.getRow())  .build();
    }
    public int upBoardCmt(CmtEntity entity){
        return mapper.upBoardCmt(entity);
    }
    public int delboardCmt(CmtDellDto dto){
        return mapper.delboardCmt(dto);
    }
}
