package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.MailCreatorService;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    MailCreatorService mailCreatorService;

    private static final String SUBJECT = "Tasks: Once a day email";

    public String createMessage(long size) {
        String message = "Currently in database you got: " + size;
        if (size == 1) {
            message += " task";
        } else {
            message += " tasks";
        }
        return message;
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        simpleEmailService.send
                (new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                createMessage(taskRepository.count())
        ));
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void sendHtmlInformationMail() {
        simpleEmailService.sendHtmlEmail
                (new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "")
                );
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }
}
