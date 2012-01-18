package com.michael.repository;

import com.michael.model.Member;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface MemberRepository extends GraphRepository<Member> {
    
    Member findByMemberId(Long memberId);
    
}
