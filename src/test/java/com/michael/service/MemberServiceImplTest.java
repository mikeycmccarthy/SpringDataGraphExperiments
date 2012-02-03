package com.michael.service;

import com.michael.model.Member;
import com.michael.model.Referral;
import com.michael.repository.MemberRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/graph-test-context.xml"})
public class MemberServiceImplTest extends TestCase {
    
    @Resource
    private MemberService memberService;

    @Resource
    private MemberRepository memberRepository;

    @Test
    public void testCreateMemberSavesFields() throws Exception {
        Long memberId = 201L;
        memberService.createMember(memberId);
        Member retrievedMember = memberRepository.findByPropertyValue("memberId", memberId);
        assertEquals(memberId, retrievedMember.getMemberId());
    }

    @Test
    public void testFindMemberThatHasBeenCreated() throws Exception {
        Long memberId = 201L;
        saveMember(memberId);
        Member member = memberService.findMember(memberId);
        assertEquals(memberId, member.getMemberId());
    }

    @Test
    public void testRefer() throws Exception {
        memberService.refer(10L,20L);
    }

    private void saveMember(Long firstMemberId) {
        memberRepository.save(new Member(firstMemberId));
    }


}
