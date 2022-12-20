package br.edu.uepb.example.firstmicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uepb.example.firstmicroservice.dtos.TeacherAssociateStudentDTO;
import br.edu.uepb.example.firstmicroservice.exceptions.SameStudentAtProjectException;
import br.edu.uepb.example.firstmicroservice.models.ProjectModel;
import br.edu.uepb.example.firstmicroservice.models.StudentModel;
import br.edu.uepb.example.firstmicroservice.models.TeacherAssociateStudentModel;
import br.edu.uepb.example.firstmicroservice.models.TeacherModel;
import br.edu.uepb.example.firstmicroservice.repositories.ProjectRepository;
import br.edu.uepb.example.firstmicroservice.repositories.StudentRepository;
import br.edu.uepb.example.firstmicroservice.repositories.TeacherAssociateStudentRepository;
import br.edu.uepb.example.firstmicroservice.repositories.TeacherRepository;

@Service
public class TeacherAssociateStudentService {

    @Autowired
    private TeacherAssociateStudentRepository teacherAssociateStudentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private StudentRepository studentRepository;

    public TeacherAssociateStudentDTO insertStudentIntoProject(TeacherAssociateStudentDTO dto) throws Exception {
        TeacherAssociateStudentModel entity = new TeacherAssociateStudentModel();

        TeacherModel teacher = teacherRepository.findById(dto.getTeacherId()).get();
        StudentModel student = studentRepository.findById(dto.getStudentId()).get();
        ProjectModel project = projectRepository.findById(dto.getProjectId()).get();

        for (TeacherAssociateStudentModel i : teacherAssociateStudentRepository.findAll()) {

            if (i.getStudent().getId().equals(student.getId()) || i.getTeacher().getId().equals(teacher.getId()) || i.getProject().getId().equals(project.getId())) {
                throw new SameStudentAtProjectException("Mesmo aluno e professor e projeto");
            }
        }
        
        entity.setTeacher(teacher);
        entity.setStudent(student);
        entity.setProject(project);
        
        student.setFunction(dto.getFunction());
        
        studentRepository.save(student);

        teacherAssociateStudentRepository.save(entity);
        return new TeacherAssociateStudentDTO(entity);
    }
}
