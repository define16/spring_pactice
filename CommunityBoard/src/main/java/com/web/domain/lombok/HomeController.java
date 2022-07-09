package com.web.domain.lombok;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Logger;

@Slf4j
@Controller
public class HomeController {
    // @Slf4j로 추가됨
    // private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String home(Locale locale, Board board) {
        log.info("Hello, The client locale is " + locale + ".");

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a h시 m분 s초");
        String formattedNow = now.format(formatter);
        log.info("Server time is " + formattedNow + ".");
        board.setRegDate(now);
        return "Home";
    }
}
