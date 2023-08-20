package com.example.jqa.member.dao;

import com.example.jqa.member.domain.Email;
import com.example.jqa.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryInterface extends JpaRepository<Member, Long> {
    //비워있어도 잘 작동함.
    // long 이 아니라 Long으로 작성. ex) int => Integer 같이 primitive형식 사용못함

    // findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
    public List<Member> findById(String id);

    public List<Member> findByName(String name);

    //like검색도 가능
    public List<Member> findByNameLike(String keyword);
    public Optional<Member> findByEmail(Email email);
}
