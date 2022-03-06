package mateuszteam.final_project.controller;

import mateuszteam.final_project.mail.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Controller
public class EmailController {
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @RequestMapping("/mailik")
    public String send(){
        Context context=new Context();
        context.setVariable("header", "moja treść maila");
        context.setVariable("tytuł1", "szablon maila1");
        context.setVariable("opis", "treść opisu");
        String body = templateEngine.process("template", context);
        emailSender.sendEmail("ersteb@gmail.com", "to jest mój tytuł", body);

        return "template";
    }
}
