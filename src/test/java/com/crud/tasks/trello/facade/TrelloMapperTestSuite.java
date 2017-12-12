package com.crud.tasks.trello.facade;

import com.crud.tasks.trello.domain.*;
import com.crud.tasks.trello.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    TrelloMapper trelloMapper;


    @Test
    public void shouldReturnTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "list", false);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);
        trelloBoardDtos.add(new TrelloBoardDto("board", "1", trelloListsDto));

        //When
        List<TrelloBoard> mappedList = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        Assert.assertEquals("1", mappedList.get(0).getId());
        Assert.assertEquals("board", mappedList.get(0).getName());
        Assert.assertEquals(trelloListsDto.get(0).getName(), mappedList.get(0).getLists().get(0).getName());
        Assert.assertEquals(trelloListsDto.get(0).getId(), mappedList.get(0).getLists().get(0).getId());
        Assert.assertEquals(trelloListsDto.get(0).isClosed(), mappedList.get(0).getLists().get(0).isClosed());

    }


    @Test
    public void shouldReturnTrelloBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "list", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        trelloBoards.add(new TrelloBoard("board", "2", trelloLists));

        //When
        List<TrelloBoardDto> mappedListDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assert.assertEquals("2", mappedListDto.get(0).getId());
        Assert.assertEquals("board", mappedListDto.get(0).getName());
        Assert.assertEquals(trelloLists.get(0).getId(), mappedListDto.get(0).getLists().get(0).getId());
        Assert.assertEquals(trelloLists.get(0).getName(),  mappedListDto.get(0).getLists().get(0).getName());
        Assert.assertEquals(trelloLists.get(0).isClosed(),  mappedListDto.get(0).getLists().get(0).isClosed());

    }

    @Test
    public void shouldReturnTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Task",
                "Description",
                "top",
                "3"
        );
        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);

        // Then
        Assert.assertEquals("3", mappedCard.getListId());
    }

    @Test
    public void shouldReturnTrelloCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard(
                "Task",
                "Description",
                "top",
                "4"
        );
        //When
        TrelloCardDto mappedCardDto = trelloMapper.mapToCardDto(trelloCard);

        // Then
        Assert.assertEquals("Task", mappedCardDto.getName());
        Assert.assertEquals("Description", mappedCardDto.getDescription());
        Assert.assertEquals("top", mappedCardDto.getPos());
        Assert.assertEquals("4", mappedCardDto.getListId());

    }

}
