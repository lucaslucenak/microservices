package br.edu.uepb.example.firstmicroservice.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.edu.uepb.example.firstmicroservice.dtos.SubjectDTO;
import br.edu.uepb.example.firstmicroservice.services.StudentService;
import br.edu.uepb.example.firstmicroservice.services.SubjectService;

@RestController
@RequestMapping("/turmas")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> findAllSubjects() {
        return ResponseEntity.ok().body(subjectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> findSubjectById(@PathVariable Long id) {
        return ResponseEntity.ok().body(subjectService.findById(id));
    }

    @PostMapping
    private ResponseEntity<SubjectDTO> saveSubject(@RequestBody SubjectDTO subjectDTO) {
        subjectDTO = subjectService.insertStudentIntoSubject(subjectDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subjectDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(subjectDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<SubjectDTO> updateSubject(@PathVariable Long id, @RequestBody SubjectDTO subjectDTO) {
        return ResponseEntity.ok().body(subjectService.update(id, subjectDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteSubjectById(@PathVariable Long id) {
        subjectService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @PatchMapping("/{subject_id}/matricularAluno/{student_id}")
//    private String linkStudentWithSubject(@PathVariable("subject_id") Long subjectId, @PathVariable("student_id") Long studentId) {
//        SubjectDTO subjectDTO = subjectService.findById(subjectId);
//        StudentDTO studentDTO = studentService.findById(studentId);
//
//        subjectDTO.getStudents().add(studentDTO);
//        subjectService.insert(subjectDTO);
//        return "redirect:/sei la";
//    }
}