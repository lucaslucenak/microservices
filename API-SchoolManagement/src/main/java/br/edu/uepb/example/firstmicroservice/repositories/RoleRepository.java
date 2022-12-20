package br.edu.uepb.example.firstmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uepb.example.firstmicroservice.models.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
}
