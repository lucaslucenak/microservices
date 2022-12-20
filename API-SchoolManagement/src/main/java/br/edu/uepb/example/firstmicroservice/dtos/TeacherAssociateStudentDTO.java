package br.edu.uepb.example.firstmicroservice.dtos;

import br.edu.uepb.example.firstmicroservice.models.TeacherAssociateStudentModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAssociateStudentDTO {

    private Long id;
    private Long teacherId;
    private Long projectId;
    private Long studentId;
    
    private String function;

    public TeacherAssociateStudentDTO(TeacherAssociateStudentModel teacherAssociateStudentModel) {
        this.id = teacherAssociateStudentModel.getId();
        this.teacherId = teacherAssociateStudentModel.getTeacher().getId();
        this.projectId = teacherAssociateStudentModel.getProject().getId();
        this.studentId = teacherAssociateStudentModel.getStudent().getId();
    }

}
