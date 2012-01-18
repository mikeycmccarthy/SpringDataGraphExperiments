package com.michael.repository;

import com.michael.model.Friendship;
import com.michael.model.Member;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

public interface MemberRepository extends GraphRepository<Member>, RelationshipOperationsRepository<Friendship> {
    
    Member findByMemberId(Long memberId);
    
}
