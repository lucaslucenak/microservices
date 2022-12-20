package br.edu.uepb.example.firstmicroservice.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.uepb.example.firstmicroservice.dtos.ProjectDTO;
import br.edu.uepb.example.firstmicroservice.dtos.StudentDTO;
import br.edu.uepb.example.firstmicroservice.exceptions.ResourceNotFoundException;
import br.edu.uepb.example.firstmicroservice.models.ProjectModel;
import br.edu.uepb.example.firstmicroservice.models.StudentModel;
import br.edu.uepb.example.firstmicroservice.models.TeacherModel;
import br.edu.uepb.example.firstmicroservice.repositories.ProjectRepository;
import br.edu.uepb.example.firstmicroservice.repositories.StudentRepository;
import br.edu.uepb.example.firstmicroservice.repositories.TeacherRepository;

@Service
public class ProjectService {
	
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public List<ProjectDTO> findAll(){
        return projectRepository.findAll().stream().map(x -> new ProjectDTO(x, x.getStudents())).toList();
    }

    public ProjectDTO findById(Long id) {
    	ProjectModel projectModel = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject"));
        return new ProjectDTO(projectModel, projectModel.getStudents());
    }
    
    public ProjectDTO insertStudentIntoProject(ProjectDTO dto) {
    	ProjectModel entity = new ProjectModel();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        TeacherModel teacher = teacherRepository.findById(dto.getTeacherId()).get();
        entity.setTeacher(teacher);
        entity.getStudents().clear();

        for (StudentDTO i : dto.getStudents()) {
            StudentModel studentModel = studentRepository.findById(i.getId()).get();
            entity.getStudents().add(studentModel);

        }
        projectRepository.save(entity);
        return new ProjectDTO(entity);
    }

    public ProjectDTO update(Long id, ProjectDTO dto) {
        try {
        	ProjectModel entity = projectRepository.findById(id).get();
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            TeacherModel teacher = teacherRepository.findById(dto.getTeacherId()).get();
            entity.setTeacher(teacher);
            projectRepository.save(entity);
            return new ProjectDTO(entity);
        }
        catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Subject id: " + id);
        }
    }

    public void delete(Long id) {
        try {
        	projectRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Subject id: " + id);
        }
    }
}

