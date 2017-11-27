package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.trello.domain.CreatedTrelloCardDto;
import com.crud.tasks.trello.domain.TrelloCardDto;
import com.crud.tasks.trello.service.TrelloService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldSendEmail() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card", "Some card", "top", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "Card", "http://test.com");

        when(adminConfig.getAdminMail()).thenReturn("test@admin.pl");
        when(trelloClient.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        trelloService.createTrelloCard(trelloCardDto);

        //Then
        Mockito.verify(emailService, times(1)).send(any());

    }

}
