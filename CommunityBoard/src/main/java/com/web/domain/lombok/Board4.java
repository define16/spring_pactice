package com.web.domain.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@ToString
@Builder   // Buildedr 패턴을 사용할 수 있게 함
public class Board4 {
    private int boardNo;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;

    // @Builder 인해 추가 되는 코드
//    Board4(int boardNo, String title, String content, String writer, LocalDateTime regDate) {
//        super();
//        this.boardNo = boardNo;
//        this.title = title;
//        this.content = content;
//        this.writer = writer;
//        this.regDate = regDate;
//    }
//
//    public static Board4Builder builder(){
//        return new Board4Builder();
//    }
//    public static class Board4Builder {
//        private int boardNo;
//        private String title;
//        private String content;
//        private String writer;
//        private LocalDateTime regDate;
//
//        private Board4Builder() {}
//
//        public Board4Builder boardNo(int boardNo){
//            this.boardNo = boardNo;
//            return this;
//        }
//
//        public Board4Builder title(String title){
//            this.title = title;
//            return this;
//        }
//        public Board4Builder content(String content){
//            this.content = content;
//            return this;
//        }
//
//        public Board4Builder writer(String writer){
//            this.writer = writer;
//            return this;
//        }
//
//        public Board4Builder regDate(LocalDateTime regDate){
//            this.regDate = regDate;
//            return this;
//        }
//
//        public Board4 build(){
//            return new Board4(boardNo, title, content, writer, regDate);
//        }
//    }




}
