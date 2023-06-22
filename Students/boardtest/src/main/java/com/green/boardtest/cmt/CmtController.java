package com.green.boardtest.cmt;

import com.green.boardtest.cmt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class CmtController {
    private final CmtService service;

    @Autowired
    public CmtController(CmtService service) {
        this.service = service;
    }
    @PostMapping("/{iboard}/cmt")
    public int postBoardCmt(@PathVariable int iboard, @RequestBody CmtInsDto dto){
        CmtEntity entity = new CmtEntity();
        entity.setIboard(iboard);
        entity.setCtnt(dto.getCtnt());
        entity.setIuser(dto.getIuser());
        return service.insBoardCmt(entity);
    }
    @GetMapping("/{iboard}/cmt")
    public CmtRes getBoardCmt(@PathVariable int iboard
    ,@RequestParam int page, @RequestParam (defaultValue = "5")int row){
        CmtSelDto dto = new CmtSelDto();
        dto.setIboard(iboard);
        dto.setPage(page);
        dto.setRow(row);
        return service.selBoardCmt(dto);
    }
    @PutMapping("/cmt/{iboardcmt}")
    public int putBoardCmt(@PathVariable int iboardcmt
            , @RequestBody CmtUpDto dto){
        CmtEntity entity = new CmtEntity();
        entity.setIboardcmt(iboardcmt);
        entity.setIuser(dto.getIuser());
        entity.setCtnt(dto.getCtnt());
        return service.upBoardCmt(entity);
    }

    @DeleteMapping("/cmt/{iboardCmt}")
    public int delBoardCmt(@PathVariable int iboardCmt,
                           @RequestParam int iuser){
        CmtDellDto dto = new CmtDellDto();
        dto.setIboardCmt(iboardCmt);
        dto.setIuser(iuser);
        return service.delboardCmt(dto);
    }
}
