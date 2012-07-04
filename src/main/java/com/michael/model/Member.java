package com.michael.model;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Set;

@NodeEntity
public class Member {

    @GraphId
    private Long graphId;

    @Indexed(unique = true)
    private Long memberId;

    /**
     * SDN currently does not support RelatedToVia on a single reference, hence the set
     * @see https://jira.springsource.org/browse/DATAGRAPH-182
     */
    @RelatedToVia(type = "REFERRAL", direction = Direction.INCOMING)
    private Set<Referral> referers;

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
        if(!referers.isEmpty()){
            return referers.iterator().next();
        } else {
            return null;
        }
    }

    public Set<Referral> getReferees() {
        return referees;
    }

}
