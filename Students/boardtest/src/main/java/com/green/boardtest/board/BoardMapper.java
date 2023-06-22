package com.green.boardtest.board;

import com.green.boardtest.board.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity dto);
    List<BoardVo> selBoard(BoardSelDto dto);
    int selBoardRowCount();
    int upBoard(BoardUpDto dto);
    int delBoard(BoardDelDto dto);
    BoardDetailVo selBoardDetail(BoardSelDto dto);
}
