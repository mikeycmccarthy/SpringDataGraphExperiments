package com.michael.repository;

import com.michael.model.Group;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 *
 */
public interface GroupRepository extends GraphRepository<Group> {
    Group findByGroupId(Long groupId);
}
