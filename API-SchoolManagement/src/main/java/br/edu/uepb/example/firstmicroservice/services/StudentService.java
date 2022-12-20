package br.edu.uepb.example.firstmicroservice.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.uepb.example.firstmicroservice.dtos.StudentDTO;
import br.edu.uepb.example.firstmicroservice.exceptions.ResourceNotFoundException;
import br.edu.uepb.example.firstmicroservice.models.ProjectModel;
import br.edu.uepb.example.firstmicroservice.models.StudentModel;
import br.edu.uepb.example.firstmicroservice.repositories.ProjectRepository;
import br.edu.uepb.example.firstmicroservice.repositories.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;
    
    @Autowired
    private ProjectRepository projectRepository;

    public List<StudentDTO> findAll(){
        return repository.findAll().stream().map(x -> new StudentDTO(x)).toList();
    }

    public StudentDTO findById(Long id) {
        return new StudentDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student")));
    }

    public StudentDTO insert(StudentDTO dto) {
        StudentModel entity = new StudentModel();
        BeanUtils.copyProperties(dto, entity);
        ProjectModel project = projectRepository.findById(dto.getProjectId()).get();
        entity.setProject(project);
        repository.save(entity);
        return new StudentDTO(entity);
    }

    public StudentDTO update(Long id, StudentDTO dto) {
        try {
            StudentModel entity = repository.findById(id).get();
            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setRegistration(dto.getRegistration());
            repository.save(entity);
            return new StudentDTO(entity);
        }
        catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Student id: " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Student id: " + id);
        }
    }
}
