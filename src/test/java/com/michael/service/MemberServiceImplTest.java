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
        saveMember(memberId);
        Member member = memberService.findMember(memberId);
        assertEquals(memberId, member.getMemberId());
    }
    
    @Test
    public void testAddReferral() throws Exception {

        Long firstMemberId = 201L;
        saveMember(firstMemberId);

        Member firstMember = memberService.findMember(firstMemberId);

        Long secondMemberId = 202L;
        saveMember(secondMemberId);

        Member secondMember = memberService.findMember(secondMemberId);

        firstMember.refer(secondMember);

        memberRepository.save(firstMember);

        Member firstMemberAfterPersistence = memberService.findMember(firstMemberId);
        Member secondMemberAfterPersistence = memberService.findMember(secondMemberId);

        Set<Referral> refereesOfFirstMember = firstMemberAfterPersistence.getReferees();
        assertEquals(1, refereesOfFirstMember.size());
        assertNull(firstMemberAfterPersistence.getReferer());

        assertTrue(secondMemberAfterPersistence.getReferees().isEmpty());
        assertNotNull(secondMemberAfterPersistence.getReferer());

        Referral firstMemberRefereesRelationship = refereesOfFirstMember.iterator().next();
        assertEquals(firstMemberId, firstMemberRefereesRelationship.getReferrer().getMemberId());
        assertEquals(secondMemberId, firstMemberRefereesRelationship.getReferee().getMemberId());

    }





    private void saveMember(Long firstMemberId) {
        memberRepository.save(new Member(firstMemberId));
    }


}
