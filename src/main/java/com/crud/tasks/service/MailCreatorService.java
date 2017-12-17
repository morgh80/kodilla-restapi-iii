package com.crud.tasks.service;

import com.crud.tasks.scheduler.EmailScheduler;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private EmailScheduler emailScheduler;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allowes sentding tasks to Trello");
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/kodilla-frontend/");
        context.setVariable("button","Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("goodbye_message", "Have a nice day");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildTaksNewsletter() {
        Context context = new Context();
        context.setVariable("message", emailScheduler.createMessage(emailScheduler.getTaskRepository().count()));
        context.setVariable("tasks_url", "http://localhost:8888/kodilla-frontend/");
        context.setVariable("button","Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("goodbye_message", "Have a nice day");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        return templateEngine.process("mail/task_newsletter", context);

    }

}
