package com.api.mensalistas.repositories;

import com.api.mensalistas.models.PagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoModel, UUID> {
    boolean existsByAno(short ano);
    boolean existsByMes(short mes);
}
