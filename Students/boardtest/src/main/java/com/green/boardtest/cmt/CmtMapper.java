package com.green.boardtest.cmt;

import com.green.boardtest.cmt.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmtMapper {
   int insBoardCmt(CmtEntity entity);
   List<CmtVo> selBoardCmt(CmtSelDto dto);
   int upBoardCmt(CmtEntity entity);
   int delboardCmt(CmtDellDto dto);
   int selBoardCmtRowCountByIBoard(int iboard);
}
