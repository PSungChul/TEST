package com.study.garibi.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public Member joinUser(Member member) {
        return memberRepository.save(member);
    }

}
