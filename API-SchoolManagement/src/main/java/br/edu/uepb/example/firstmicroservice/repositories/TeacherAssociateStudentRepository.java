package br.edu.uepb.example.firstmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.uepb.example.firstmicroservice.models.TeacherAssociateStudentModel;

public interface TeacherAssociateStudentRepository extends JpaRepository<TeacherAssociateStudentModel, Long> {
}
