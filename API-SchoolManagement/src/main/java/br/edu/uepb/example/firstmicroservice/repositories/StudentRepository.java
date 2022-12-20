package br.edu.uepb.example.firstmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uepb.example.firstmicroservice.models.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {
}
