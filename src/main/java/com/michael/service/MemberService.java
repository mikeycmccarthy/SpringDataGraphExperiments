package com.michael.service;

import com.michael.model.Member;

public interface MemberService {

    Member createMember(Long memberId);

    Member findMember(Long memberId);

    void refer(Long referer, Long referee);

}
