package com.crud.tasks.trello.facade;

import com.crud.tasks.trello.domain.*;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @InjectMocks
    TrelloValidator trelloValidator;
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloValidatorTest.class);


    @Test
    public void shouldValidateTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "my_list", false));
        List<TrelloBoard> boardsToFilter = new ArrayList<>();
        boardsToFilter.add(new TrelloBoard("1", "Test", trelloLists));
        boardsToFilter.add(new TrelloBoard("2", "Board", trelloLists));


        //When
        List<TrelloBoard> filterdBoards = trelloValidator.validateTrelloBoards(boardsToFilter);
        //Then
        Assert.assertEquals(1, filterdBoards.size());
    }
}
