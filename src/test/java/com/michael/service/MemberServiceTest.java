package com.michael.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/graph-test-context.xml"})
public class MemberServiceTest {

    @Resource
    private MemberService memberService;

    @Test
    public void testRefer() throws Exception {
        memberService.refer(10L, 20L);
    }
}
