package com.michael.model;

import org.springframework.data.neo4j.annotation.*;

@RelationshipEntity(type = "REFERRAL")
public class Referral {

    @GraphId
    private Long graphId;

    @EndNode
    @Fetch
    private Member referee;

    @StartNode
    @Fetch
    private Member referrer;

    public Referral() {
    }

    public Referral(Member referrer, Member referee) {
        this.referrer = referrer;
        this.referee = referee;
    }

    public Member getReferee() {
        return referee;
    }

    public Member getReferrer() {
        return referrer;
    }
}
