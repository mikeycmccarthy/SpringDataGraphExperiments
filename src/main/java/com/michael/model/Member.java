package com.michael.model;

import org.springframework.data.neo4j.annotation.*;

import java.util.Set;

@NodeEntity
public class Member {

    @GraphId
    private Long graphId;

    @Indexed
    private Long memberId;

    @Fetch
    @RelatedToVia(type = "FRIENDS_WITH")
    private Set<Friendship> friendships;

    public Member() {
    }

    public Member(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void addFriendship(Member otherMember){
        friendships.add(new Friendship(this, otherMember, 12345L));
    }

}
