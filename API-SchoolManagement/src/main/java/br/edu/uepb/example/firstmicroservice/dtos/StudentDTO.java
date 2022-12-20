package br.edu.uepb.example.firstmicroservice.dtos;

import br.edu.uepb.example.firstmicroservice.models.StudentModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id;
    private String name;
    private String registration;
    private String email;
    private Long projectId;
    
    private String function;

    public StudentDTO(StudentModel studentModel) {
        id = studentModel.getId();
        name = studentModel.getName();
        registration = studentModel.getRegistration();
        email = studentModel.getEmail();
        projectId = studentModel.getProject().getId();
    }
    
    public StudentDTO(StudentModel studentModel, String error) {
        id = studentModel.getId();
        name = studentModel.getName();
        registration = studentModel.getRegistration();
        email = studentModel.getEmail();
        projectId = studentModel.getProject().getId();
        function = studentModel.getFunction();
    }
}
