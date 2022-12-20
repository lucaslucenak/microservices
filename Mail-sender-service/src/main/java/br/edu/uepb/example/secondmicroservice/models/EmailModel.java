package br.edu.uepb.example.secondmicroservice.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.uepb.example.secondmicroservice.enums.EmailStatus;

@Entity
@Table(name = "tb_mail")
public class EmailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime localDateTime = LocalDateTime.now();
    @Enumerated(value = EnumType.STRING)
    private EmailStatus emailStatus;

    public EmailModel(String emailTo, String subject, String text, EmailStatus emailStatus) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.text = text;
        this.emailStatus = emailStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public EmailStatus getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(EmailStatus emailStatus) {
        this.emailStatus = emailStatus;
    }
}