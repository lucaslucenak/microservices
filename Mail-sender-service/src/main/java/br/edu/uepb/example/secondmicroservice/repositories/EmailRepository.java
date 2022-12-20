package br.edu.uepb.example.secondmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uepb.example.secondmicroservice.models.EmailModel;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
