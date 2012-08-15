package com.michael.model;

import java.util.Date;
import lombok.Getter;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 *
 */
@RelationshipEntity(type="MEMBER_OF")
public class MemberOf {
    public static final String TYPE = "MEMBER_OF";
    
    @StartNode
    private Group group;
    
    @EndNode
    private Person person;
    
    private Long createdOn;
    private Long deactivatedOn;
    
    @Getter
    private String role;
    
    @Getter
    @GraphId
    private Long id;

    public MemberOf() {
    }
    
    public MemberOf(Group group, Person person, String role) {
        this.group = group;
        this.person = person;
        this.role = role;
        this.createdOn = new Date().getTime();
    }
    
    public boolean isActive() {
        return (deactivatedOn==null);
    }
    
    public void deActivate() {
        this.deactivatedOn = new Date().getTime();
    }
}
