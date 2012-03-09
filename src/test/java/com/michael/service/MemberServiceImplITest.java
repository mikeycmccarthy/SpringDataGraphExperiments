package com.michael.service;

import com.michael.model.Member;
import com.michael.repository.MemberRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.event.TransactionData;
import org.neo4j.graphdb.event.TransactionEventHandler;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/graph-test-context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MemberServiceImplITest extends TestCase {

    @Resource
    private MemberService memberService;

    @Resource
    private MemberRepository memberRepository;

    @Resource
    private GraphDatabaseService graphDatabaseService;

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
        memberService.createMember(memberId);
        Member member = memberService.findMember(memberId);
        assertEquals(memberId, member.getMemberId());
    }

    @Test
    public void testTransactionHandlerIsCalledInCommit() throws Exception {
        TransactionEventHandler transactionEventHandler = mock(TransactionEventHandler.class);
        graphDatabaseService.registerTransactionEventHandler(transactionEventHandler);
        memberService.refer(10L, 20L);
        verify(transactionEventHandler).beforeCommit(any(TransactionData.class));
    }

    @Test
    public void testUniquenessInMultiThreadedScenario() throws Exception {

        final Long firstMember = 100L;
        
        final List<Exception> exceptionList = new ArrayList<Exception>();

        ExecutorService exec = Executors.newFixedThreadPool(16);
        for (int i = 101; i < 1000; i++) {
            final Long buddyId = new Long(i);
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        memberService.refer(firstMember, buddyId);
                    } catch (NoSuchElementException noSuchElementException) {
                        exceptionList.add(noSuchElementException);
                    }
                }
            }

            );
        }

        exec.shutdown();
        exec.awaitTermination(50, TimeUnit.SECONDS);
        
        assertTrue("Exceptions found when creating nodes", exceptionList.isEmpty());
        
    }

    @Test
    public void testRefer() throws Exception {
        memberService.refer(10L, 20L);
    }

}
