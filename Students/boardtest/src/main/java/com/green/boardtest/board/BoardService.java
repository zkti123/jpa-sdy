package com.green.boardtest.board;

import com.green.boardtest.board.model.*;
import com.green.boardtest.cmt.CmtService;
import com.green.boardtest.cmt.model.CmtRes;
import com.green.boardtest.cmt.model.CmtSelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
   private final BoardMapper mapper;
   private final CmtService cmtService;

    @Autowired
    public BoardService(BoardMapper mapper, CmtService cmtService) {
        this.mapper = mapper;
        this.cmtService = cmtService;
    }


    public int insBoard(BoardInsDto dto){
        BoardEntity entity = new BoardEntity();
        entity.setTitle(dto.getTitle());
        entity.setCtnt(dto.getCtnt());
        entity.setIuser(dto.getIuser());
       int result =  mapper.insBoard(entity);
       if (result == 1){
           return entity.getIboard();
       }
       return result;
    }
    public List<BoardVo> selBoard(BoardSelDto dto){
        int page = dto.getPage() - 1;

        dto.setStartIdx(page*dto.getRow());
       return mapper.selBoard(dto);
    }
    public int selBoardMaxPage(int row){
       int count = mapper.selBoardRowCount();
        return (int)Math.ceil((double) count / row);
    }
    public int upBoard(BoardUpDto dto){
        return mapper.upBoard(dto);
    }
    public int delBoard(BoardDelDto dto){
        return mapper.delBoard(dto);
    }

    public BoardCmtDetailVo2 selBoardDetail(BoardSelDto dto){
       BoardDetailVo vo = mapper.selBoardDetail(dto);

        CmtSelDto cmtDto = new CmtSelDto();
        cmtDto.setIboard(dto.getIboard());
        cmtDto.setPage(1);
        cmtDto.setRow(5);
        CmtRes cmt = cmtService.selBoardCmt(cmtDto);
        return BoardCmtDetailVo2.builder().board(vo).cmt(cmt).build();

    }
}
