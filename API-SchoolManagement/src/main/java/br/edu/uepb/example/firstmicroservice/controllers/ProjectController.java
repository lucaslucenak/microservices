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

import br.edu.uepb.example.firstmicroservice.dtos.ProjectDTO;
import br.edu.uepb.example.firstmicroservice.services.ProjectService;

@RestController
@RequestMapping("/projetos")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> findAllStudents() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findStudentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    private ResponseEntity<ProjectDTO> saveStudent(@RequestBody ProjectDTO projectDTO) {
        projectDTO = service.insertStudentIntoProject(projectDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(projectDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(projectDTO);
    }

    @PutMapping("/{id}")
   private ResponseEntity<ProjectDTO> updateStudent(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
       return ResponseEntity.ok().body(service.update(id, projectDTO));
    }

   @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
      }
}
