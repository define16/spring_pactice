package com.example.jqa.member.application;

import com.example.jqa.member.domain.Member;
import com.example.jqa.member.dao.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        memberRepository.findAll().forEach(e -> members.add(e));
        return members;
    }

    public Optional<Member> findById(Long mbrNo) {
        Optional<Member> member = memberRepository.findById(mbrNo);
        return member;
    }

    public void deleteById(Long mbrNo) {

        memberRepository.deleteById(mbrNo);
    }

    public Member save(Member member) {
        memberRepository.save(member);
        return member;
    }

    public void updateById(Long mbrNo, Member member) {
        Optional<Member> e = memberRepository.findById(mbrNo);

        if (e.isPresent()) {
            e.get().setMemberNo(member.getMemberNo());
            e.get().setId(member.getId());
            e.get().setName(member.getName());
            memberRepository.save(member);
        }
    }

}
