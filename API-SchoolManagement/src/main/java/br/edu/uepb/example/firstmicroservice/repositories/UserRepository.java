package br.edu.uepb.example.firstmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uepb.example.firstmicroservice.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	UserModel findByEmail(String email);
}
