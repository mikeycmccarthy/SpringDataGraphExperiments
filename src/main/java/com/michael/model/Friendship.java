package com.michael.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="FRIENDS_WITH")
public class Friendship {

    @GraphId
    private Long graphId;
    
    @StartNode
    private Member initiatingMember;

    @EndNode
    private Member otherMember;
    
    private Long createdOn;

    public Friendship() {
    }

    public Friendship(Member initiatingMember, Member otherMember, Long createdOn) {
        this.initiatingMember = initiatingMember;
        this.otherMember = otherMember;
        this.createdOn = createdOn;
    }

    public Member getInitiatingMember() {
        return initiatingMember;
    }

    public Member getOtherMember() {
        return otherMember;
    }

    public Long getCreatedOn() {
        return createdOn;
    }
}
