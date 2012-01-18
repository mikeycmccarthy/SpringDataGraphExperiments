package com.michael.service;

import com.michael.model.Member;

public interface MemberService {

    void createMember(Long memberId);

    Member findMember(Long memberId);

}
