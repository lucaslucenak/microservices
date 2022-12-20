package br.edu.uepb.example.secondmicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.edu.uepb.example.secondmicroservice.dto.EmailDTO;
import br.edu.uepb.example.secondmicroservice.enums.EmailStatus;
import br.edu.uepb.example.secondmicroservice.models.EmailModel;
import br.edu.uepb.example.secondmicroservice.repositories.EmailRepository;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;
    
    @Autowired
    private EmailRepository repository;

    public void sendEmail(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(emailDTO.getEmailTo());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getText());
        EmailModel emailModel = new EmailModel(emailDTO.getEmailTo(), emailDTO.getSubject(), emailDTO.getText(), EmailStatus.SUCCESS);
        try {
            mailSender.send(message);
            emailModel.setEmailStatus(EmailStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e);
            emailModel.setEmailStatus(EmailStatus.ERROR);
        } finally {
        	repository.save(emailModel);
        }
    }
}