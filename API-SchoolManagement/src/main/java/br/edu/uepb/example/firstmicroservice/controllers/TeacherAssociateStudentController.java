package br.edu.uepb.example.firstmicroservice.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.uepb.example.firstmicroservice.dtos.TeacherAssociateStudentDTO;
import br.edu.uepb.example.firstmicroservice.services.TeacherAssociateStudentService;

@RestController
@RequestMapping("/associar-projeto")
public class TeacherAssociateStudentController {

    @Autowired
    private TeacherAssociateStudentService service;

    @PostMapping
    private ResponseEntity<TeacherAssociateStudentDTO> associateStudent(@RequestBody TeacherAssociateStudentDTO teacherAssociateStudentDTO) throws Exception {
        teacherAssociateStudentDTO = service.insertStudentIntoProject(teacherAssociateStudentDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teacherAssociateStudentDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(teacherAssociateStudentDTO);
    }
}
