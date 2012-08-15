package com.michael.model;

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
public class Group {
    
    @Getter
    @Indexed(indexName = "groups")
    private Long groupId;
    
    @Getter
    @GraphId
    private Long id;
    
    public Group(Long groupId) {
        this.groupId = groupId;
    }

    public Group() {
    }
    
    @Getter
    @RelatedToVia(type=MemberOf.TYPE, direction= Direction.OUTGOING)
    private Set<MemberOf> persons;

}
