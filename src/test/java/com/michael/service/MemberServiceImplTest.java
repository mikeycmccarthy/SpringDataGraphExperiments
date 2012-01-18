package com.michael.service;

import com.michael.model.Friendship;
import com.michael.model.Member;
import com.michael.repository.MemberRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/graph-test-context.xml"})
@Transactional
public class MemberServiceImplTest extends TestCase {
    
    @Resource
    private MemberServiceImpl memberService;

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
        memberRepository.save(new Member(memberId));
        Member member = memberService.findMember(memberId);
        assertEquals(memberId, member.getMemberId());
    }
    
    @Test
    public void testAddFriend() throws Exception {
        Long firstMemberId = 201L;
        memberRepository.save(new Member(firstMemberId));
        Member firstMember = memberService.findMember(firstMemberId);
        Long secondMemberId = 202L;
        memberRepository.save(new Member(secondMemberId));
        Member secondMember = memberService.findMember(secondMemberId);

        firstMember.addFriendship(secondMember);
        memberRepository.save(firstMember);
        Member firstMemberAfterPersistence = memberService.findMember(firstMemberId);
        System.out.println("MemberServiceImplTest.testAddFriend");

    }
    
    
}
