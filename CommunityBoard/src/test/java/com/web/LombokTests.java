package com.web;

import com.web.domain.lombok.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LombokTests {
    @Test
    public void testBoard() {
        Board board1 = new Board();
        board1.setBoardNo(1);
        board1.setTitle("title1");

        Board board2 = new Board();
        board2.setBoardNo(2);
        board2.setTitle("title1");

        Board board3 = new Board();
        board3.setBoardNo(3);
        board3.setTitle("title1");

        Set<Board> boardSet = new HashSet<Board>();
        boardSet.add(board1);
        boardSet.add(board2);
        boardSet.add(board3);

        System.out.println("저장된 데이터 수 : " + boardSet.size());
        for (Board board : boardSet) {
            System.out.println(board);
        }
    }

    @Test
    public void testUserItem() {
        UserItem userItem1 = new UserItem();
        userItem1.setUserNo(1);
        userItem1.setItemId(100);

        UserItem userItem2 = new UserItem();
        userItem2.setUserNo(2);
        userItem2.setItemId(100);

        UserItem userItem3 = new UserItem();
        userItem3.setUserNo(3);
        userItem3.setItemId(200);
        Set<UserItem> userItemSet = new HashSet<UserItem>();
        userItemSet.add(userItem1);
        userItemSet.add(userItem2);
        userItemSet.add(userItem3);

        System.out.println("저장된 데이터 수 : " + userItemSet.size());
        for (UserItem userItem : userItemSet) {
            System.out.println(userItem);
        }
    }

    @Test
    public void testStudent() {
        Student student1 = new Student();
        student1.setStudentNo(1);
        student1.setName("A");

        Student student2 = new Student();
        student2.setStudentNo(2);
        student2.setName("B");

        Student student3 = new Student();
        student3.setStudentNo(3);
        student3.setName("C");

        Set<Student> studentSet = new HashSet<Student>();
        studentSet.add(student1);
        studentSet.add(student2);
        studentSet.add(student3);

        System.out.println("저장된 데이터 수 : " + studentSet.size());
        for (Student student : studentSet) {
            System.out.println(student);
        }
    }

    @Test
    public void testNoArgsConstructor() {
        Board board = new Board();
        System.out.println(board);
    }

    @Test
    public void testNoArgsConstructor2() {
        Member member = new Member();
        System.out.println(member);
    }
}
