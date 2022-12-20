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
import br.edu.uepb.example.firstmicroservice.dtos.StudentDTO;
import br.edu.uepb.example.firstmicroservice.services.StudentService;

@RestController
@RequestMapping("/alunos")
public class StudentController {

    @Autowired
    private StudentService service;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    public String EXCHANGE_NAME;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAllStudents() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findStudentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    private ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
    	studentDTO = service.insert(studentDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentDTO.getId()).toUri();
        getMessage(studentDTO.getEmail(), "Aluno " + studentDTO.getName() + " criado!", "O aluno foi cadastro com sucesso em nosso sistema.");
        return ResponseEntity.created(uri).body(studentDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        getMessage(studentDTO.getEmail(), "Aluno " + studentDTO.getName() + " atualizado!", "O aluno foi atualizado com sucesso em nosso sistema.");
    	return ResponseEntity.ok().body(service.update(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
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
