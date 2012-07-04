package com.michael.service;

import com.michael.model.Member;
import com.michael.model.Referral;
import com.michael.repository.MemberRepository;
import org.neo4j.graphdb.Node;
import org.neo4j.kernel.GraphDatabaseAPI;
import org.neo4j.kernel.impl.transaction.LockManager;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.transaction.SystemException;

import static org.neo4j.helpers.collection.MapUtil.map;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberRepository memberRepository;

    @Resource
    private Neo4jTemplate template;

    @Resource
    private GraphDatabaseAPI graphDatabaseAPI;

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
        try {
            lock(template.getReferenceNode());
            Member refererMember = getOrCreateMember(referer);
            Member refereeMember = getOrCreateMember(referee);
            // a bit faster
            // template.createRelationshipBetween(refererMember,refereeMember,Referral.class,"REFERRAL",false);
            refererMember.refer(refereeMember); template.save(refererMember);
        } finally {
            unlock(template.getReferenceNode());
        }
    }

    private void lock(Node node) {
        LockManager lockManager = graphDatabaseAPI.getLockManager();
        lockManager.getWriteLock(node);
    }

    private void unlock(Node node) {
        try {
            LockManager lockManager = graphDatabaseAPI.getLockManager();
            lockManager.releaseWriteLock(node, graphDatabaseAPI.getTxManager().getTransaction());
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
    }

    private Member getOrCreateMember(long memberId) {

        Member member = memberRepository.findByPropertyValue("memberId", memberId);

        if (member == null) {
            member = memberRepository.save(new Member(memberId));
        }

        return member;
    }

    /**
     * if the field in member is not @Indexed(unique=true) it has to be done "manuall" using the facilities
     * provided by neo4j-template
     */
    private Member getOrCreateMemberWithoutUniqueIndexedMemberId(long memberId) {
        Node node = template.getOrCreateNode("Member", "memberId", memberId, map("memberId", memberId));
        if (!node.hasProperty("__type__"))
            template.postEntityCreation(node,Member.class);
        return template.load(node,Member.class);
    }
}
