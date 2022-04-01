package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.events.OrderPlacedEvent;
import mateuszteam.final_project.domain.events.OrderReturnedEvent;
import mateuszteam.final_project.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class EmailSenderService {

    private final JavaMailSender javaMailSender;
    private final OrdersRepository ordersRepository;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public void sendMail(String to,
                         String subject,
                         String text,
                         boolean isHtmlContent) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(mailFrom);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, isHtmlContent);
        javaMailSender.send(mimeMessage);
    }

    void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    @EventListener
    void handleOrderPlacedEvent(OrderPlacedEvent event) throws Exception {
        this.sendMail(event.getUserEmail(),
                String.format("Zamówienie ID=%d złożone", event.getOrderId()),
                "Dziękujemy za złożone <bold>zamówienie</bold>!",
                true);
    }

    @EventListener
    public void handleOrderReturnedEvent(OrderReturnedEvent event) throws Exception {
        this.sendMail(event.getUserEmail(),
                String.format("Zamówienie ID=%d zgłoszone do zwrotu, prosimy o wpłątę %.2f",
                        event.getOrderId(),
                        ordersRepository.findById(event.getOrderId()).get().getTotalPrice()),
                "Zapraszamy ponownie <bold>do kolejnych zamówień </bold>!",
                true);
    }

}
