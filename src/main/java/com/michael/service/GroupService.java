package com.michael.service;

import com.michael.model.Group;

/**
 *
 */
public interface GroupService {
    
    Group save(Group group);
    
    Group find(Long groupId);
}
