package com.web.controller;

import com.web.domain.javabeans.Board;
import com.web.domain.lombok.Board3;
import com.web.domain.lombok.Board4;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/boards")
public class BoardController {
    @GetMapping
    public ResponseEntity<List<Board3>> list() {
        log.info("list");

        List<Board3> boardList = new ArrayList<>();

        Board3 board = new Board3(1);

        board.setTitle("제목1");
        board.setWriter("글쓴이1");
        board.setContent("내용1");
        board.setRegDate(LocalDateTime.now());
        boardList.add(board);

        board = new Board3(2);

        board.setTitle("제목2");
        board.setWriter("글쓴이2");
        board.setContent("내용2");
        board.setRegDate(LocalDateTime.now());
        boardList.add(board);

        ResponseEntity<List<Board3>> entity = new ResponseEntity<>(boardList, HttpStatus.OK);

        return entity;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody Board3 board) {
        log.info("register");
        ResponseEntity<String> entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);

        return entity;
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<Board3> read() {
        log.info("list");


        Board3 board = new Board3(1);

        board.setTitle("제목1");
        board.setWriter("글쓴이1");
        board.setContent("내용1");
        board.setRegDate(LocalDateTime.now());

        ResponseEntity<Board3> entity = new ResponseEntity<>(board, HttpStatus.OK);

        return entity;
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<String> remove(@PathVariable("boardNo") int boardNo) {
        log.info("remove");

        ResponseEntity<String> entity = new ResponseEntity<>("SUCCESS",HttpStatus.OK);

        return entity;
    }

    @PutMapping("/{boardNo}")
    public ResponseEntity<Board3> modify(@PathVariable("boardNo") int boardNo, @RequestBody Board3 board) {
        log.info("modify");

        ResponseEntity<Board3> entity = new ResponseEntity<>(board, HttpStatus.OK);

        return entity;
    }
}
