package com.web.domain.lombok;

import lombok.*;

import java.time.LocalDateTime;

@Data   // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode
public class Board3 {
    @NonNull
    private int boardNo;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;

}
