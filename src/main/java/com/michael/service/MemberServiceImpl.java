package com.michael.service;

import com.michael.model.Member;
import com.michael.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberRepository memberRepository;

    @Override
    public Member createMember(Long memberId) {
        return memberRepository.save(new Member(memberId));
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findByPropertyValue("memberId", memberId);
    }

    @Override
    public void refer(Long referer, Long referee) {
        Member refererMember = getOrCreateMember(referer);
        Member refereeMember = getOrCreateMember(referee);
        refererMember.refer(refereeMember);
        memberRepository.save(refererMember);
    }

    private Member getOrCreateMember(long memberId) {

        Member member = memberRepository.findByPropertyValue("memberId", memberId);

        if (member == null) {
            member = memberRepository.save(new Member(memberId));
        }

        return member;
    }


}
