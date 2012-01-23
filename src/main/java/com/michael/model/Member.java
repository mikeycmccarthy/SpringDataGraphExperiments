package com.michael.model;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Set;

@NodeEntity
public class Member {

    @GraphId
    private Long graphId;

    @Indexed
    private Long memberId;

    @RelatedToVia(type = "REFERRAL", direction = Direction.INCOMING)
    private Referral referer;

    @RelatedToVia(type = "REFERRAL", direction = Direction.OUTGOING)
    private Set<Referral> referees;

    public Member() {
    }

    public Member(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void refer(Member memberToRefer){
        referees.add(new Referral(this, memberToRefer));
    }

    public Referral getReferer() {
        return referer;
    }

    public Set<Referral> getReferees() {
        return referees;
    }


}
