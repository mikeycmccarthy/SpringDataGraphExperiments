package com.michael.service;

import com.michael.model.Group;
import com.michael.model.MemberOf;
import com.michael.model.Person;
import java.util.Set;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/graph-test-context.xml"})
public class GroupITest {
    
    private static final Long FIRST_PERSON_ID = 1L;
    private static final Long SECOND_PERSON_ID = 2L;
    
    private static final Long FIRST_GROUP_ID = 1L;
    private static final Long SECOND_GROUP_ID = 2L;
    
    private static final String FIRST_ROLE = "role1";
    private static final String SECOND_ROLE = "role2";
    
    @Resource
    private GroupService groupService;
    
    @Resource
    private PersonService personService;
    
    @Test
    public void testCreateMultipleActiveLinks() {
        //Person, group and relationship
        Person person = new Person(FIRST_PERSON_ID);
        Group group = new Group(FIRST_GROUP_ID);
        person.assignToGroup(group, FIRST_ROLE);
        
        groupService.save(group);
        personService.save(person);
        
        //Second active relationship
        person = personService.find(FIRST_PERSON_ID);
        assertNotNull(person);
        group = groupService.find(FIRST_GROUP_ID);
        assertNotNull(group);
        person.assignToGroup(group, SECOND_ROLE);
        
        groupService.save(group);
        personService.save(person);
        
        //verify
        group = groupService.find(FIRST_GROUP_ID);
        Set<MemberOf> persons = group.getPersons();
        assertThat(persons.size(), is(equalTo(2)));
        
        
    }

}
