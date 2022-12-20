package br.edu.uepb.example.firstmicroservice.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.uepb.example.firstmicroservice.dtos.StudentDTO;
import br.edu.uepb.example.firstmicroservice.dtos.SubjectDTO;
import br.edu.uepb.example.firstmicroservice.exceptions.ResourceNotFoundException;
import br.edu.uepb.example.firstmicroservice.models.StudentModel;
import br.edu.uepb.example.firstmicroservice.models.SubjectModel;
import br.edu.uepb.example.firstmicroservice.models.TeacherModel;
import br.edu.uepb.example.firstmicroservice.repositories.StudentRepository;
import br.edu.uepb.example.firstmicroservice.repositories.SubjectRepository;
import br.edu.uepb.example.firstmicroservice.repositories.TeacherRepository;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public List<SubjectDTO> findAll(){
        return subjectRepository.findAll().stream().map(x -> new SubjectDTO(x, x.getStudents())).toList();
    }

    public SubjectDTO findById(Long id) {
        SubjectModel subjectModel = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject"));
        return new SubjectDTO(subjectModel, subjectModel.getStudents());
    }

    public SubjectDTO insert(SubjectDTO dto) {
        SubjectModel entity = new SubjectModel();
        entity.setName(dto.getName());
        entity.setRoom(dto.getRoom());
        TeacherModel teacher = teacherRepository.findById(dto.getTeacherId()).get();
        entity.setTeacher(teacher);
        subjectRepository.save(entity);
        return new SubjectDTO(entity);
    }

    public SubjectDTO update(Long id, SubjectDTO dto) {
        try {
            SubjectModel entity = subjectRepository.findById(id).get();
            entity.setName(dto.getName());
            entity.setRoom(dto.getRoom());
            TeacherModel teacher = teacherRepository.findById(dto.getTeacherId()).get();
            entity.setTeacher(teacher);
            subjectRepository.save(entity);
            return new SubjectDTO(entity);
        }
        catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException("Subject id: " + id);
        }
    }

    public void delete(Long id) {
        try {
            subjectRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Subject id: " + id);
        }
    }

    public SubjectDTO insertStudentIntoSubject(SubjectDTO dto) {
        SubjectModel entity = new SubjectModel();
        entity.setName(dto.getName());
        entity.setRoom(dto.getRoom());
        TeacherModel teacher = teacherRepository.findById(dto.getTeacherId()).get();
        entity.setTeacher(teacher);
        entity.getStudents().clear();

        for (StudentDTO i : dto.getStudents()) {
            StudentModel studentModel = studentRepository.findById(i.getId()).get();
            entity.getStudents().add(studentModel);

        }
        subjectRepository.save(entity);
        return new SubjectDTO(entity);
    }
}
