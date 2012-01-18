package com.michael.service;

import com.michael.model.Member;
import com.michael.repository.MemberRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberRepository memberRepository;

    @Override
    public void createMember(Long memberId) {
        memberRepository.save(new Member(memberId));
    }

}
