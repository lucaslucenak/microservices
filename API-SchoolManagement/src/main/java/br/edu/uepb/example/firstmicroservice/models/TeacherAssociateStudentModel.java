package br.edu.uepb.example.firstmicroservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_teacher_associate_student")
public class TeacherAssociateStudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private TeacherModel teacher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private ProjectModel project;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentModel student;

}
