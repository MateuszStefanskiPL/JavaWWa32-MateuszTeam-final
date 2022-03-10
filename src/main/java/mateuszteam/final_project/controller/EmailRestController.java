package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Controller
public class EmailRestController {

    private final EmailSenderService mailService;

    @GetMapping("/email")
    ResponseEntity<String> sendMail() throws Exception {
        mailService.sendMail("prz.wozniak@hotmail.com",
                "Zamówienie złożone",
                "Dziękujemy za złożone <bold>zamówienie</bold>!",
                true);
        return ResponseEntity.ok("Mail wysłany");
    }
}
