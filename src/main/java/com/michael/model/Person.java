package com.michael.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

/**
 *
 */
@NodeEntity
public class Person {
    @Getter
    @Indexed(indexName = "persons")
    private Long personId;
    
    @Getter
    @GraphId
    private Long id;
    
    public Person(Long personId) {
        this.personId = personId;
        this.groups = new HashSet();
    }

    public Person() {
    }
    
    @RelatedToVia(type=MemberOf.TYPE, direction= Direction.INCOMING)
    private Set<MemberOf> groups;
    
    public MemberOf assignToGroup(Group group, String role) {
        MemberOf memberOf = new MemberOf(group, this, role);
        groups.add(memberOf);
        return memberOf;
    }

}
