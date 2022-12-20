package br.edu.uepb.example.firstmicroservice.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_subject")
public class SubjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String room;
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private TeacherModel teacher;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "tb_subject_has_student", 
      joinColumns = @JoinColumn(name = "subject_id"), 
      inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<StudentModel> students =  new HashSet<>();
}
