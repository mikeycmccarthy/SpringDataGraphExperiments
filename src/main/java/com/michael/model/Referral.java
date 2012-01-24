package com.michael.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "REFERRAL")
public class Referral {

    @GraphId
    private Long graphId;

    @EndNode
    private Member referee;

    @StartNode
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
