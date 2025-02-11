package com.api.mensalistas.repositories;

import com.api.mensalistas.models.JogadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JogadorRepository extends JpaRepository<JogadorModel, UUID> {

    boolean existsByNome(String nome);
    boolean existsByEmail(String email);
    JogadorModel findByNome(String nome);
}
