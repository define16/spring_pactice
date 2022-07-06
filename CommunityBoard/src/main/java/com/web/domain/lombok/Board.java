package com.web.domain.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="boardNo")
public class Board {
    private int boardNo;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;

    // @EqualsAndHashCode 인해 생겨남
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + boardNo;
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        Board other = (Board) obj;
//        return boardNo == other.boardNo;
//    }

}
