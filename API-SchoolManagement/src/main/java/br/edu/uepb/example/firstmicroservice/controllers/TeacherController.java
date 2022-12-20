package br.edu.uepb.example.firstmicroservice.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.uepb.example.firstmicroservice.dtos.EmailDTO;
import br.edu.uepb.example.firstmicroservice.dtos.TeacherDTO;
import br.edu.uepb.example.firstmicroservice.services.TeacherService;

@RestController
@RequestMapping("/professores")
public class TeacherController {

    @Autowired
    private TeacherService service;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    public String EXCHANGE_NAME;

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> findAllTeachers() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> findTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    private ResponseEntity<TeacherDTO> saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        teacherDTO = service.insert(teacherDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teacherDTO.getId()).toUri();
        getMessage(teacherDTO.getEmail(), "Professor " + teacherDTO.getName() + " criado!", "Seu cadastro como professor foi criado com sucesso!. Matricula: " + teacherDTO.getRegistration());
        return ResponseEntity.created(uri).body(teacherDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        getMessage(teacherDTO.getEmail(), "Professor " + teacherDTO.getName() + " atualizado!", "Seu cadastro como professor foi atualizado com sucesso!. Matricula: " + teacherDTO.getRegistration());
    	return ResponseEntity.ok().body(service.update(id, teacherDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteTeacherById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    public void getMessage(String email, String title, String text) {
        try {
            EmailDTO emailDTO = new EmailDTO(email, title, text);
            String json = new ObjectMapper().writeValueAsString(emailDTO);
            Message message = MessageBuilder.withBody(json.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
