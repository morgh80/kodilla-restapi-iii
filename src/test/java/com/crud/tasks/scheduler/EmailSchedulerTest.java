package com.crud.tasks.scheduler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Test
    public void shouldCreateProperMessage() {
        //When
        String singularTestMessage = emailScheduler.createMessage(1);
        String pluralTestMessage = emailScheduler.createMessage(2);

        //Then
        Assert.assertEquals("Currently in database you got: 1 task", singularTestMessage);
        Assert.assertEquals("Currently in database you got: 2 tasks", pluralTestMessage);
    }

}