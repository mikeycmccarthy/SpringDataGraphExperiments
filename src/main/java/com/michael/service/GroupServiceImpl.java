package com.michael.service;

import com.michael.model.Group;
import com.michael.repository.GroupRepository;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupRepository groupRepository;
    
    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group find(Long groupId) {
        return groupRepository.findByPropertyValue("groupId", groupId);
        //return groupRepository.findByGroupId(groupId);
    }

}
