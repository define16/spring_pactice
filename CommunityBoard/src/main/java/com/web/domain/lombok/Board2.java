package com.web.domain.lombok;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Board2 {
    @NonNull
    private int boardNo;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;

    // @RequiredArgsConstructor 인해 생겨남
//    public Board2(int boardNo) {
//        super();
//        this.boardNo = boardNo;
//    }

}
